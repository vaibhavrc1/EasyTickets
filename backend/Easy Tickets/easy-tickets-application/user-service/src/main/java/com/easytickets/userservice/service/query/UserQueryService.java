package com.easytickets.userservice.service.query;

import com.easytickets.userservice.dto.UserResponse;

import java.util.List;

public interface UserQueryService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}
