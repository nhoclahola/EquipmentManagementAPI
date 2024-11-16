package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.auth.UserLoginRequest;
import com.nhoclahola.equipmentmanagementapi.entities.Role;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.auth.InvalidUserNameOrPasswordException;
import com.nhoclahola.equipmentmanagementapi.exceptions.auth.WrongRoleException;
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
    public String userAuthenticate(UserLoginRequest request)
    {
        User user = userService.findByUsername(request.getUsername());
        boolean authenticated = user.getPassword().equals(request.getPassword());
        if (!authenticated)
            throw new InvalidUserNameOrPasswordException();
        if (user.getRole() != Role.USER)
            throw new WrongRoleException("Người dùng đăng nhập không phải là trợ giảng");
        String jwt = JwtProvider.generateJwtToken(user);
        return jwt;
    }

    @Override
    public String adminAuthenticate(UserLoginRequest request)
    {
        User user = userService.findByUsername(request.getUsername());
        boolean authenticated = user.getPassword().equals(request.getPassword());
        if (!authenticated)
            throw new InvalidUserNameOrPasswordException();
        if (user.getRole() != Role.ADMIN)
            throw new WrongRoleException("Người dùng đăng nhập không phải là quản trị viên");
        String jwt = JwtProvider.generateJwtToken(user);
        return jwt;
    }
}
