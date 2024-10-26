package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.services.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class BorrowRequestController
{
    private final BorrowRequestService borrowRequestService;

    @PutMapping("/borrow-request/{requestId}/approve")
    public ResponseEntity<BorrowRequestResponse> approveBorrowRequest(@PathVariable Long requestId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.approveBorrowRequest(requestId));
    }

    @PutMapping("/borrow-request/{requestId}/reject")
    public ResponseEntity<BorrowRequestResponse> rejectBorrowRequest(@PathVariable Long requestId)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.rejectBorrowRequest(requestId));
    }

    @GetMapping("/borrow-request")
    public ResponseEntity<List<BorrowRequestResponse>> getAllBorrowRequest(@RequestParam("page") int pageNumber)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.findAllBorrowRequest(pageNumber));
    }
}
