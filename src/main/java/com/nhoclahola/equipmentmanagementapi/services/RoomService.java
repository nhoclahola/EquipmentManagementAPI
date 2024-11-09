package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService
{
    long count();

    List<RoomWithStatusResponse> findAllRooms(int pageNumber);

    RoomResponse createRoom(RoomCreateRequest request);

    void deleteRoom(Long roomId);

    RoomResponse editRoom(Long roomId, RoomEditRequest request);

    Room findById(Long roomId);

    long countSearchRooms(String query);

    List<RoomWithStatusResponse> searchRooms(String query, int pageNumber);

    Page<RoomWithStatusResponse> findAllRoomsPage(int pageNumber);

    Page<RoomWithStatusResponse> searchRoomsPage(String query, int pageNumber);
}
