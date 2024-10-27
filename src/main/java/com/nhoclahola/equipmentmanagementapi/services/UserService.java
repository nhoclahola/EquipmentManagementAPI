package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.user.request.UserEditRequest;
import com.nhoclahola.equipmentmanagementapi.entities.User;

import java.util.List;

public interface UserService
{
    long count();

    List<User> findUsers(int pageNumber);

    User createUser(UserCreateRequest request);

    void deleteUser(Long userId);

    User editUser(Long userId, UserEditRequest request);

    User findById(Long userId);
}
