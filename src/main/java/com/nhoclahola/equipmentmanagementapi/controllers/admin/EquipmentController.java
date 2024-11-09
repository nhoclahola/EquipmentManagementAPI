package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.request.EquipmentEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class EquipmentController
{
    private final EquipmentService equipmentService;

    @GetMapping("/equipments/count")
    public ResponseEntity<Long> getEquipmentCount()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.count());
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentResponse>> getAllEquipments(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.findAllEquipments(pageNumber - 1));
    }

    @GetMapping("/equipments/{id}")
    public ResponseEntity<EquipmentWithRooms> getEquipmentById(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.findByIdResponse(id));
    }

    @PostMapping("/equipments")
    public ResponseEntity<EquipmentResponse> createCategory(@RequestPart String equipmentName,
                                                            @RequestPart(required = false) MultipartFile image) throws IOException
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipmentService.createEquipment(equipmentName, image));
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<EquipmentResponse> editCategory(@PathVariable Long id,
                                                          @RequestPart String equipmentName,
                                                          @RequestPart(required = false) MultipartFile image) throws IOException
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.editEquipment(id, equipmentName, image));
    }

    @GetMapping("/equipments/room/{roomId}")
    public ResponseEntity<List<EquipmentWithQuantity>> getRoomsEquipments(@PathVariable Long roomId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.findRoomsEquipments(roomId));
    }

    @GetMapping("/equipments/all-quantities")
    public ResponseEntity<List<EquipmentWithTotalQuantityInAllRooms>> getAllEquipmentsWithAllQuantities(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.findAllEquipmentWithTotalQuantityInAllRooms(pageNumber - 1));
    }

    @GetMapping("/equipments/search/all-quantities")
    public ResponseEntity<List<EquipmentWithTotalQuantityInAllRooms>> searchAllEquipmentsWithAllQuantities(@RequestParam("query") String query, @RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.searchEquipmentWithTotalQuantityInAllRooms(query, pageNumber - 1));
    }

    @GetMapping("/equipments/search/count")
    public ResponseEntity<Long> getCountSearchEquipments(@RequestParam("query") String query)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.countSearchEquipments(query));
    }
}
