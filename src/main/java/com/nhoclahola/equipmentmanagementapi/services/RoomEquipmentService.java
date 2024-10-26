package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;

public interface RoomEquipmentService
{
    RoomEquipmentResponse addEquipmentToRoom(Long roomId, Long equipmentId, int quantity);

    RoomEquipmentResponse editEquipmentQuantityInRoom(Long roomId, Long equipmentId, int quantity);

    void deleteEquipmentInRoom(Long roomId, Long equipmentId);

    RoomEquipment findByRoomIdAndEquipmentId(Long roomId, Long equipmentId);

    int findTotalQuantityByRoomAndEquipment(Long roomId, Long equipmentId);
}
