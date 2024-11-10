package com.nhoclahola.equipmentmanagementapi.controllers;

import com.nhoclahola.equipmentmanagementapi.dto.auth.UserLoginRequest;
import com.nhoclahola.equipmentmanagementapi.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-auth")
public class UserAuth
{
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.authenticate(request));
    }
}
