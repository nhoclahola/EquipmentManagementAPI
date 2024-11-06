package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.exceptions.room.RoomNameAlreadyExistsException;
import com.nhoclahola.equipmentmanagementapi.exceptions.room.RoomNotFountException;
import com.nhoclahola.equipmentmanagementapi.mapper.RoomMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.RoomRepository;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService
{
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public long count()
    {
        return roomRepository.count();
    }

    @Override
    public List<RoomWithStatusResponse> findAllRooms(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        List<RoomWithStatusResponse> roomList = roomRepository.findAllRoomsWithBorrowedStatus(pageable).stream().toList();
        return roomList;
    }

    @Override
    public RoomResponse createRoom(RoomCreateRequest request)
    {
        if (roomRepository.existsByRoomName(request.getRoomName()))
            throw new RoomNameAlreadyExistsException();
        Room room = roomMapper.roomCreateRequestToRoom(request);
        return roomMapper.toRoomResponse(roomRepository.save(room));
    }

    @Override
    public void deleteRoom(Long roomId)
    {
        if (!roomRepository.existsById(roomId))
            throw new RoomNotFountException();
        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomResponse editRoom(Long roomId, RoomEditRequest request)
    {
        if (roomRepository.existsByRoomName(request.getRoomName()))
            throw new RoomNameAlreadyExistsException();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFountException());
        roomMapper.editRoom(room, request);
        roomRepository.save(room);
        return roomMapper.toRoomResponse(room);
    }

    @Override
    public Room findById(Long roomId)
    {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFountException());
    }
}
