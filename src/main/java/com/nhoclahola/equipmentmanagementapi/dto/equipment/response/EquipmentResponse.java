package com.nhoclahola.equipmentmanagementapi.dto.equipment.response;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentResponse
{
    private String equipmentId;
    private String equipmentName;
    private String brandName;
    private String description;
    private String imageUrl;
}
