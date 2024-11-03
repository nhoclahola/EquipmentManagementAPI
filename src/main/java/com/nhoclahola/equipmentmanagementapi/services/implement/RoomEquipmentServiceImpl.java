package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentAlreadyExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNotExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.mapper.RoomEquipmentMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.RoomEquipmentRepository;
import com.nhoclahola.equipmentmanagementapi.services.EquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.RoomEquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomEquipmentServiceImpl implements RoomEquipmentService
{
    private final RoomEquipmentRepository roomEquipmentRepository;
    private final RoomEquipmentMapper roomEquipmentMapper;
    private final RoomService roomService;
    private final EquipmentService equipmentService;


    @Override
    public List<RoomEquipmentWithRemainQuantityResponse> findByRoomId(Long roomId)
    {
        List<RoomEquipmentWithRemainQuantity> roomEquipmentList = roomEquipmentRepository.findRoomEquipmentsWithRemainQuantityByRoomId(roomId);
        return roomEquipmentMapper.toListRoomEquipmentWithRemainQuantityResponse(roomEquipmentList);
    }

    @Override
    public boolean existsByRoomIdAndEquipmentId(Long roomId, Long equipmentId)
    {
        return roomEquipmentRepository.existsByRoomRoomIdAndEquipmentEquipmentId(roomId, equipmentId);
    }

    @Override
    public RoomEquipmentResponse addEquipmentToRoom(Long roomId, Long equipmentId, int quantity)
    {
        Room room = roomService.findById(roomId);
        Equipment equipment = equipmentService.findById(equipmentId);
        if (roomEquipmentRepository.existsByRoomRoomIdAndEquipmentEquipmentId(roomId, equipmentId))
            throw new EquipmentAlreadyExistsInRoomException();
        RoomEquipment roomEquipment = RoomEquipment.builder()
                .room(room)
                .equipment(equipment)
                .quantity(quantity)
                .build();
        return roomEquipmentMapper.toRoomEquipmentResponse(roomEquipmentRepository.save(roomEquipment));
    }

    @Override
    public RoomEquipmentResponse editEquipmentQuantityInRoom(Long roomId, Long equipmentId, int quantity)
    {
        RoomEquipment roomEquipment = roomEquipmentRepository.findByRoomRoomIdAndEquipmentEquipmentId(roomId, equipmentId)
                .orElseThrow(() -> new EquipmentNotExistsInRoomException());
        roomEquipment.setQuantity(quantity);
        return roomEquipmentMapper.toRoomEquipmentResponse(roomEquipmentRepository.save(roomEquipment));
    }

    @Override
    public void deleteEquipmentInRoom(Long roomId, Long equipmentId)
    {
        RoomEquipment roomEquipment = roomEquipmentRepository.findByRoomRoomIdAndEquipmentEquipmentId(roomId, equipmentId)
                .orElseThrow(() -> new EquipmentNotExistsInRoomException());
        roomEquipmentRepository.delete(roomEquipment);
    }

    @Override
    public RoomEquipmentWithRemainQuantityResponse findByRoomIdAndEquipmentId(Long roomId, Long equipmentId)
    {
        RoomEquipmentWithRemainQuantity roomEquipment = roomEquipmentRepository.findRoomEquipmentWithRemainQuantityByRoomIdAndEquipmentId(roomId, equipmentId)
                .orElseThrow(() -> new EquipmentNotExistsInRoomException());
        return roomEquipmentMapper.toRoomEquipmentWithRemainQuantityResponse(roomEquipment);
    }

    @Override
    public int findTotalQuantityByRoomAndEquipment(Long roomId, Long equipmentId)
    {
        return roomEquipmentRepository.findTotalQuantityByRoomAndEquipment(roomId, equipmentId);
    }
}
