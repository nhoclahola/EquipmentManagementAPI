package com.nhoclahola.equipmentmanagementapi.dto.equipment.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentEditRequest
{
    private String equipmentName;
    private String imageUrl;
}
