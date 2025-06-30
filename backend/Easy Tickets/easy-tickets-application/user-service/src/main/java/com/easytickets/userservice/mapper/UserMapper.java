package com.easytickets.userservice.mapper;

import com.easytickets.userservice.dto.UserRequest;
import com.easytickets.userservice.dto.UserResponse;
import com.easytickets.userservice.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity userEntity);

    UserEntity toUserEntity(UserRequest userRequest);

    void updateUserEntity(UserRequest userRequest, @MappingTarget UserEntity userEntity);
}
