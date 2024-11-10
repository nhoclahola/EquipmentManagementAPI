package com.nhoclahola.equipmentmanagementapi.controllers.user;

import com.nhoclahola.equipmentmanagementapi.dto.user.response.UserResponse;
import com.nhoclahola.equipmentmanagementapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserUserController
{
    private final UserService userService;

    @GetMapping("/from-token")
    public ResponseEntity<UserResponse> getUserFromToken()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserFromToken());
    }
}
