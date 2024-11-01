package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomEquipmentMapper
{
    RoomEquipmentResponse toRoomEquipmentResponse(RoomEquipment roomEquipment);

    List<RoomEquipmentResponse> toListRoomEquipmentResponse(List<RoomEquipment> roomEquipmentList);
}
