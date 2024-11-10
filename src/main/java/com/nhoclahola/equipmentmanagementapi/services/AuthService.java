package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.auth.UserLoginRequest;

public interface AuthService
{
    public abstract String authenticate(UserLoginRequest request);
}
