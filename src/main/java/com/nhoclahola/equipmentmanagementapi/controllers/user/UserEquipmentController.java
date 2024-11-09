package com.nhoclahola.equipmentmanagementapi.controllers.user;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.EquipmentService;
import com.nhoclahola.equipmentmanagementapi.services.RoomEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEquipmentController
{
    private final EquipmentService equipmentService;
    private final RoomEquipmentService roomEquipmentService;

    @GetMapping("/equipments/count")
    public ResponseEntity<Long> getEquipmentCount()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(equipmentService.count());
    }

    @GetMapping("/equipments")
    public ResponseEntity<Page<RoomEquipmentWithRemainQuantityResponse>> getAllEquipments(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.findAllRoomEquipmentsWithRemainQuantity(pageNumber - 1));
    }

    @GetMapping("/equipments/search")
    public ResponseEntity<Page<RoomEquipmentWithRemainQuantityResponse>> searchEquipments(@RequestParam("query") String query, @RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.searchRoomEquipmentsWithRemainQuantity(query, pageNumber - 1));
    }
}
