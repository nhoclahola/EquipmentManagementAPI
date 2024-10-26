package com.nhoclahola.equipmentmanagementapi.dto.equipment.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentCreateRequest
{
    @NotBlank(message = "Equipment name must not be blank")
    private String equipmentName;
    private String imageUrl;
}
