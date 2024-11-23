package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.CantEditOrRemoveException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentAlreadyExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNotExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.mapper.RoomEquipmentMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.RoomEquipmentRepository;
import com.nhoclahola.equipmentmanagementapi.services.EquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.RoomEquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public long countAll()
    {
        return roomEquipmentRepository.count();
    }

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
        Long totalBorrowedQuantity = roomEquipmentRepository.findEquipmentInRoomWithTotalBorrows(roomId, equipmentId);
        if (totalBorrowedQuantity > quantity)
            throw new CantEditOrRemoveException("Không thể chỉnh sửa số lượng ít hơn tổng số lượng đang được mượn");
        roomEquipment.setQuantity(quantity);
        return roomEquipmentMapper.toRoomEquipmentResponse(roomEquipmentRepository.save(roomEquipment));
    }

    @Override
    public void deleteEquipmentInRoom(Long roomId, Long equipmentId)
    {
        RoomEquipment roomEquipment = roomEquipmentRepository.findByRoomRoomIdAndEquipmentEquipmentId(roomId, equipmentId)
                .orElseThrow(() -> new EquipmentNotExistsInRoomException());
        Long totalBorrowedQuantity = roomEquipmentRepository.findEquipmentInRoomWithTotalBorrows(roomId, equipmentId);
        if (totalBorrowedQuantity > 0)
            throw new CantEditOrRemoveException("Thiết bị tại phòng này đang có người mượn chưa trả");
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

    @Override
    public Page<RoomEquipmentWithRemainQuantityResponse> findAllRoomEquipmentsWithRemainQuantity(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<RoomEquipmentWithRemainQuantity> roomEquipmentList = roomEquipmentRepository.findAllRoomEquipmentsWithRemainQuantity(pageable);
        return roomEquipmentMapper.toPageRoomEquipmentWithRemainQuantityResponse(roomEquipmentList);
    }

    @Override
    public Page<RoomEquipmentWithRemainQuantityResponse> searchRoomEquipmentsWithRemainQuantity(String query, int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Page<RoomEquipmentWithRemainQuantity> roomEquipmentList = roomEquipmentRepository.searchRoomEquipmentsWithRemainQuantity(query, pageable);
        return roomEquipmentMapper.toPageRoomEquipmentWithRemainQuantityResponse(roomEquipmentList);
    }

    @Override
    public Long findEquipmentInRoomWithTotalBorrows(Long roomId, Long equipmentId)
    {
        return roomEquipmentRepository.findEquipmentInRoomWithTotalBorrows(roomId, equipmentId);
    }

    @Override
    public long findTotalRoomEquipmentQuantity()
    {
        return roomEquipmentRepository.findTotalRoomEquipmentQuantity();
    }
}
