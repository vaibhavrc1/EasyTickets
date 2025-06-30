package com.easytickets.userservice.service.command;

import com.easytickets.userservice.dto.UserRequest;
import com.easytickets.userservice.dto.UserResponse;
import com.easytickets.userservice.exception.DuplicateEmailException;
import com.easytickets.userservice.exception.UserNotFoundException;
import com.easytickets.userservice.mapper.UserMapper;
import com.easytickets.userservice.model.UserEntity;
import com.easytickets.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserCommandServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        validateEmail(userRequest.getEmail());
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toUserResponse(savedUserEntity);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        UserEntity existingUserEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        if(!existingUserEntity.getEmail().equals(userRequest.getEmail())) {
            validateEmail(userRequest.getEmail());
        }
        userMapper.updateUserEntity(userRequest, existingUserEntity);
        return userMapper.toUserResponse(userRepository.save(existingUserEntity));
    }

    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private void validateEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("User with email " + email + " already exists.");
        }
    }
}
