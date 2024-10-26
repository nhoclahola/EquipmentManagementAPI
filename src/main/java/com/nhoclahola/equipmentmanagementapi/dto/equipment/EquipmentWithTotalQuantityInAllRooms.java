package com.nhoclahola.equipmentmanagementapi.dto.equipment;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentWithTotalQuantityInAllRooms
{
    private Long equipmentId;
    private String equipmentName;
    private String imageUrl;
    private Long totalQuantity;
    private Long remainQuantity;  // Số lượng còn lại sau khi mượn
}
