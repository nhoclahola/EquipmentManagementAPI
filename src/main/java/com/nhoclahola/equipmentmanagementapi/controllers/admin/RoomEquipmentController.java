package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.response.RoomEquipmentWithRemainQuantityResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import com.nhoclahola.equipmentmanagementapi.services.RoomEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RoomEquipmentController
{
    private final RoomEquipmentService roomEquipmentService;

    @GetMapping("/rooms/{roomId}/equipments")
    public ResponseEntity<List<RoomEquipmentWithRemainQuantityResponse>> getRoomEquipmentByRoomId(@PathVariable Long roomId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.findByRoomId(roomId));
    }

    @GetMapping("/rooms/{roomId}/equipments/{equipmentId}")
    public ResponseEntity<RoomEquipmentWithRemainQuantityResponse> getEquipmentInRoom(@PathVariable Long roomId, @PathVariable Long equipmentId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.findByRoomIdAndEquipmentId(roomId, equipmentId));
    }

    @GetMapping("/rooms/{roomId}/equipments/{equipmentId}/check")
    public ResponseEntity<Boolean> checkEquipmentIsInRoom(@PathVariable Long roomId, @PathVariable Long equipmentId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.existsByRoomIdAndEquipmentId(roomId, equipmentId));
    }

    @PostMapping("/rooms/{roomId}/add/{equipmentId}")
    public ResponseEntity<RoomEquipmentResponse> addEquipmentToRoom(@PathVariable Long roomId,
                                                    @PathVariable Long equipmentId,
                                                    @RequestParam int quantity)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomEquipmentService.addEquipmentToRoom(roomId, equipmentId, quantity));
    }

    @PutMapping("/rooms/{roomId}/edit/{equipmentId}")
    public ResponseEntity<RoomEquipmentResponse> editEquipmentQuantityInRoom(@PathVariable Long roomId,
                                                    @PathVariable Long equipmentId,
                                                    @RequestParam int quantity)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.editEquipmentQuantityInRoom(roomId, equipmentId, quantity));
    }

    @DeleteMapping("/rooms/{roomId}/delete/{equipmentId}")
    public ResponseEntity<Void> deleteEquipmentInRoom(@PathVariable Long roomId,
                                                      @PathVariable Long equipmentId)
    {
        roomEquipmentService.deleteEquipmentInRoom(roomId, equipmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room-equipments/count")
    public ResponseEntity<Long> countAll()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomEquipmentService.countAll());
    }
}
