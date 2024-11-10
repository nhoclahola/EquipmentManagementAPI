package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.RoomBorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RoomBorrowRequestController
{
    private final RoomBorrowRequestService roomBorrowRequestService;

    @PutMapping("/room-borrow-request/{requestId}/approve")
    public ResponseEntity<RoomBorrowRequestResponse> approveBorrowRequest(@PathVariable Long requestId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.approveBorrowRequest(requestId));
    }

    @PutMapping("/room-borrow-request/{requestId}/reject")
    public ResponseEntity<RoomBorrowRequestResponse> rejectBorrowRequest(@PathVariable Long requestId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.rejectBorrowRequest(requestId));
    }

    @PutMapping("/room-borrow-request/{requestId}/returned")
    public ResponseEntity<RoomBorrowRequestResponse> markAsReturnedBorrowRequest(@PathVariable Long requestId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.markAsReturnedBorrowRequest(requestId));
    }

    @GetMapping("/room-borrow-request/count")
    public ResponseEntity<Long> getBorrowRequestCount()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.count());
    }

    @GetMapping("/room-borrow-request")
    public ResponseEntity<List<RoomBorrowRequestResponse>> getAllBorrowRequest(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.findAllLatestBorrowRequest(pageNumber - 1));
    }

    @GetMapping("/room-borrow-request/count-pending")
    public ResponseEntity<Long> countPendingBorrowRequest()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomBorrowRequestService.countPendingBorrowRequest());
    }
}
