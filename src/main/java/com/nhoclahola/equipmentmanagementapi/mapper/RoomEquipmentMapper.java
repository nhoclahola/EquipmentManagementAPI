package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomEquipmentMapper
{
    RoomEquipmentResponse toRoomEquipmentResponse(RoomEquipment roomEquipment);

    RoomEquipmentWithRemainQuantityResponse toRoomEquipmentWithRemainQuantityResponse(RoomEquipmentWithRemainQuantity roomEquipment);

    List<RoomEquipmentResponse> toListRoomEquipmentResponse(List<RoomEquipment> roomEquipmentList);

    List<RoomEquipmentWithRemainQuantityResponse> toListRoomEquipmentWithRemainQuantityResponse(List<RoomEquipmentWithRemainQuantity> roomEquipmentList);
}
