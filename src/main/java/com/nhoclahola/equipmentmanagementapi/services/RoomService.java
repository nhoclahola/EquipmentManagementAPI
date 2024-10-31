package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;

import java.util.List;

public interface RoomService
{
    long count();

    List<RoomResponse> findAllRooms(int pageNumber);

    RoomResponse createRoom(RoomCreateRequest request);

    void deleteRoom(Long roomId);

    RoomResponse editRoom(Long roomId, RoomEditRequest request);

    Room findById(Long roomId);
}
