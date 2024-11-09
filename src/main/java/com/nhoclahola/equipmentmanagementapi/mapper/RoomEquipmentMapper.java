package com.nhoclahola.equipmentmanagementapi.mapper;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoomEquipmentMapper
{
    RoomEquipmentResponse toRoomEquipmentResponse(RoomEquipment roomEquipment);

    RoomEquipmentWithRemainQuantityResponse toRoomEquipmentWithRemainQuantityResponse(RoomEquipmentWithRemainQuantity roomEquipment);

    List<RoomEquipmentResponse> toListRoomEquipmentResponse(List<RoomEquipment> roomEquipmentList);

    List<RoomEquipmentWithRemainQuantityResponse> toListRoomEquipmentWithRemainQuantityResponse(List<RoomEquipmentWithRemainQuantity> roomEquipmentList);

    //    Page<RoomEquipmentWithRemainQuantityResponse> toPageRoomEquipmentWithRemainQuantityResponse(Page<RoomEquipmentWithRemainQuantity> roomEquipmentList);
    default Page<RoomEquipmentWithRemainQuantityResponse> toPageRoomEquipmentWithRemainQuantityResponse(Page<RoomEquipmentWithRemainQuantity> roomEquipmentList)
    {
        List<RoomEquipmentWithRemainQuantityResponse> content = roomEquipmentList.getContent().stream()
                .map(this::toRoomEquipmentWithRemainQuantityResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, roomEquipmentList.getPageable(), roomEquipmentList.getTotalElements());
    }
}
