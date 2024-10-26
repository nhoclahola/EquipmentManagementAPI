package com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.RequestStatus;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BorrowRequestResponse
{
    private Long id;
    private EquipmentResponse equipment;
    private RoomResponse room;
    private int quantity;
    private LocalDate requestDate;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private RequestStatus status;
}
