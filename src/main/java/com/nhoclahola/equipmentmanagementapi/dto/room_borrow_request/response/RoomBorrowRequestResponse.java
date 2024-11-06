package com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.response.EquipmentResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.dto.user.response.UserResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RequestStatus;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomBorrowRequestResponse
{
    private Long id;
    private UserResponse user;
    private RoomResponse room;
    private LocalDate requestDate;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private RequestStatus status;
}
