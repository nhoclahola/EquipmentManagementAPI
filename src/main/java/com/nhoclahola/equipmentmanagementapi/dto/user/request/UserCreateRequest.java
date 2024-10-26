package com.nhoclahola.equipmentmanagementapi.dto.user.request;

import jakarta.validation.constraints.NotBlank;import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest
{
    @NotBlank(message = "You must enter the full name")
    private String fullName;
    private Boolean gender;
    private String phoneNumber;
    private LocalDate dob;
    @NotBlank(message = "You must enter the username")
    private String username;
    @NotBlank(message = "You must enter the password")
    private String password;
}
