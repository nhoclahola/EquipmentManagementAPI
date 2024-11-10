package com.nhoclahola.equipmentmanagementapi.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLoginRequest
{
    @NotBlank(message = "Bạn cần nhập username")
    private String username;
    @NotBlank(message = "Bạn cần nhập password")
    private String password;
}
