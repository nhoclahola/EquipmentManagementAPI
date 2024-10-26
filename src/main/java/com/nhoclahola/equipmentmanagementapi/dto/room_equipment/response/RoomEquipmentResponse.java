package com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomEquipmentResponse
{
    private Long id;
    private RoomResponse room;
    private EquipmentResponse equipment;
    private int quantity;
}
