package com.easytickets.userservice.service.command;

import com.easytickets.userservice.dto.UserRequest;
import com.easytickets.userservice.dto.UserResponse;

public interface UserCommandService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUserById(Long id);
}
