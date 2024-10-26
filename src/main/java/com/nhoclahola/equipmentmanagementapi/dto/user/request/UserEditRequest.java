package com.nhoclahola.equipmentmanagementapi.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEditRequest
{
    private String fullName;
    private Boolean gender;
    private String phoneNumber;
    private LocalDate dob;
    private String username;
    private String password;
}
