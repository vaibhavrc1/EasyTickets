package com.easytickets.userservice.service.query;

import com.easytickets.userservice.dto.UserResponse;
import com.easytickets.userservice.exception.UserNotFoundException;
import com.easytickets.userservice.mapper.UserMapper;
import com.easytickets.userservice.model.UserEntity;
import com.easytickets.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapper.toUserResponse(userEntity);
    }
}
