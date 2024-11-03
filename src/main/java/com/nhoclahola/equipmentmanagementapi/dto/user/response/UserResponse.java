package com.nhoclahola.equipmentmanagementapi.dto.user.response;

import com.nhoclahola.equipmentmanagementapi.entities.Role;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse
{
    private Long userId;
    private String fullName;
    private Boolean gender;
    private String phoneNumber;
    private Role role;
    private String username;
}
