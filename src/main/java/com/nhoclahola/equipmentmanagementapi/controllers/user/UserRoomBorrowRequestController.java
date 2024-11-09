package com.nhoclahola.equipmentmanagementapi.controllers.user;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request.BorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.request.RoomBorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import com.nhoclahola.equipmentmanagementapi.services.RoomBorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRoomBorrowRequestController
{
    private final RoomBorrowRequestService roomBorrowRequestService;

    @PostMapping("/room-borrow-request")
    public ResponseEntity<RoomBorrowRequestResponse> createBorrowRequest(@RequestBody RoomBorrowRequestRequest request) {

        RoomBorrowRequestResponse borrowRequest = roomBorrowRequestService.createRoomBorrowRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRequest);
    }
}
