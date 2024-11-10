package com.nhoclahola.equipmentmanagementapi.dto.equipment;

import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EquipmentWithRooms
{
    private Long equipmentId;
    private String equipmentName;
    private String brandName;
    private String description;
    private String imageUrl;
    private List<RoomResponse> rooms;
}
