package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper
{
    Room roomCreateRequestToRoom(RoomCreateRequest request);

    RoomResponse toRoomResponse(Room room);

    List<RoomResponse> toListRoomResponse(List<Room> roomList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void editRoom(@MappingTarget Room room, RoomEditRequest request);
}
