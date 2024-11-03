package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;

import java.util.List;

public interface RoomEquipmentService
{
    List<RoomEquipmentWithRemainQuantityResponse> findByRoomId(Long roomId);

    boolean existsByRoomIdAndEquipmentId(Long roomId, Long equipmentId);

    RoomEquipmentResponse addEquipmentToRoom(Long roomId, Long equipmentId, int quantity);

    RoomEquipmentResponse editEquipmentQuantityInRoom(Long roomId, Long equipmentId, int quantity);

    void deleteEquipmentInRoom(Long roomId, Long equipmentId);

    RoomEquipmentWithRemainQuantityResponse findByRoomIdAndEquipmentId(Long roomId, Long equipmentId);

    int findTotalQuantityByRoomAndEquipment(Long roomId, Long equipmentId);
}
