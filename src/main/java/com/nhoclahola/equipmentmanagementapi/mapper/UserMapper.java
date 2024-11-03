package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.user.response.UserResponse;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    UserResponse toUserResponse(User user);

    User userCreateRequestToUser(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void editUser(@MappingTarget User user, UserEditRequest request);
}
