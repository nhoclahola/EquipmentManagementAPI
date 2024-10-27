package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNameAlreadyExistException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNotFoundException;
import com.nhoclahola.equipmentmanagementapi.mapper.EquipmentMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.EquipmentRepository;
import com.nhoclahola.equipmentmanagementapi.services.EquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService
{
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    private final FileUploadService fileUploadService;

    // Relative path in project
    private static final String EQUIPMENT_DIR = "/equipments/";

    @Override
    public long count()
    {
        return equipmentRepository.count();
    }

    @Override
    public List<EquipmentResponse> findAllEquipments(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        List<Equipment> equipmentList = equipmentRepository.findAll(pageable).stream().toList();
        return equipmentMapper.toListEquipmentResponse(equipmentList);
    }

    @Override
    public EquipmentResponse createEquipment(String equipmentName, MultipartFile image) throws IOException
    {
        if (equipmentRepository.existsByEquipmentName(equipmentName))
            throw new EquipmentNameAlreadyExistException();
        String imageUrl = null;
        if (image != null && image.getContentType().startsWith("image"))
            imageUrl = fileUploadService.upload(EQUIPMENT_DIR, image);
        Equipment equipment = Equipment.builder()
                .equipmentName(equipmentName)
                .imageUrl(imageUrl)
                .build();
        return equipmentMapper.toEquipmentResponse(equipmentRepository.save(equipment));
    }

    @Override
    public void deleteEquipment(Long equipmentId)
    {
        if (!equipmentRepository.existsById(equipmentId))
            throw new EquipmentNotFoundException();
        equipmentRepository.deleteById(equipmentId);
    }

    @Override
    public EquipmentResponse editEquipment(Long equipmentId, String equipmentName, MultipartFile image) throws IOException
    {
        if (equipmentRepository.existsByEquipmentName(equipmentName))
            throw new EquipmentNameAlreadyExistException();
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException());
        equipment.setEquipmentName(equipmentName);
        if (image != null && image.getContentType().startsWith("image"))
        {
            fileUploadService.deleteFile(equipment.getImageUrl());
            String imageUrl = fileUploadService.upload(EQUIPMENT_DIR, image);
            equipment.setImageUrl(imageUrl);
        }
        equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(equipment);
    }

    @Override
    public Equipment findById(Long equipmentId)
    {
        return equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException());
    }

    @Override
    public EquipmentWithRooms findByIdResponse(Long equipmentId)
    {
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException());
        return equipmentMapper.toEquipmentWithRooms(equipment);
    }

//    @Override
//    public EquipmentWithRooms findEquipmentWithRoomsById(Long equipmentId)
//    {
//        return equipmentRepository.findEquipmentWithRoomsById(equipmentId)
//                .orElseThrow(() -> new EquipmentNotFoundException());
//    }

    @Override
    public List<EquipmentWithQuantity> findRoomsEquipments(Long roomId)
    {
        return equipmentRepository.findEquipmentsWithQuantityByRoomId(roomId);
    }

    @Override
    public List<EquipmentWithTotalQuantityInAllRooms> findAllEquipmentWithTotalQuantityInAllRooms(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return equipmentRepository.findAllEquipmentsWithTotalQuantity(pageable);
    }
}
