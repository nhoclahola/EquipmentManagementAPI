package com.nhoclahola.equipmentmanagementapi.dto.equipment;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentWithQuantity
{
    private Long equipmentId;
    private String equipmentName;
    private String brandName;
    private String description;
    private String imageUrl;
    private int quantity;
    private Long remainingQuantity;
}
