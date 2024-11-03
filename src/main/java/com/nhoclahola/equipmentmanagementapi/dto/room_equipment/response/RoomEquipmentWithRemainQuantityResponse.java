package com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomEquipmentWithRemainQuantityResponse
{
    private Long id;
    private RoomResponse room;
    private EquipmentResponse equipment;
    private long quantity;
    private long remainQuantity;
}
