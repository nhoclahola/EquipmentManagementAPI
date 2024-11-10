package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.auth.UserLoginRequest;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.auth.InvalidUserNameOrPasswordException;
import com.nhoclahola.equipmentmanagementapi.security.JwtProvider;
import com.nhoclahola.equipmentmanagementapi.services.AuthService;
import com.nhoclahola.equipmentmanagementapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
    private final UserService userService;


    @Override
    public String authenticate(UserLoginRequest request)
    {
        User user = userService.findByUsername(request.getUsername());
        boolean authenticated = user.getPassword().equals(request.getPassword());
        if (!authenticated)
            throw new InvalidUserNameOrPasswordException();
        String jwt = JwtProvider.generateJwtToken(user);
        return jwt;
    }
}
