package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoomMapper.class) // Roomapper to use toRoomResponse method
public interface EquipmentMapper
{
    Equipment equipmentCreateRequestToEquipment(EquipmentCreateRequest request);

    @Mapping(target = "rooms", source = "roomEquipments") // Map từ roomEquipments sang rooms
    EquipmentWithRooms toEquipmentWithRooms(Equipment equipment);

    default Room mapRoomEquipmentToRoom(RoomEquipment roomEquipment)
    {
        Room room = roomEquipment.getRoom();
        return room;
    }

    EquipmentResponse toEquipmentResponse(Equipment equipment);

    List<EquipmentResponse> toListEquipmentResponse(List<Equipment> equipmentList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void editEquipment(@MappingTarget Equipment equipment, EquipmentEditRequest request);
}
