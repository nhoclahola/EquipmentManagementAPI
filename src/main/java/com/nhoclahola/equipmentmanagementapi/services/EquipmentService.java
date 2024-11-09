package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EquipmentService
{
    long count();

    List<EquipmentResponse> findAllEquipments(int pageNumber);

    EquipmentResponse createEquipment(String equipmentName, MultipartFile image) throws IOException;

    void deleteEquipment(Long equipmentId);

    EquipmentResponse editEquipment(Long equipmentId, String equipmentName, MultipartFile image) throws IOException;

    Equipment findById(Long equipmentId);

//    EquipmentWithRooms findEquipmentWithRoomsById(Long equipmentId);

    EquipmentWithRooms findByIdResponse(Long equipmentId);

    List<EquipmentWithQuantity> findRoomsEquipments(Long roomId);

    List<EquipmentWithTotalQuantityInAllRooms> findAllEquipmentWithTotalQuantityInAllRooms(int pageNumber);

    long countSearchEquipments(String query);

    List<EquipmentWithTotalQuantityInAllRooms> searchEquipmentWithTotalQuantityInAllRooms(String query, int pageNumber);
}
