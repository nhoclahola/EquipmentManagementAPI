package com.nhoclahola.equipmentmanagementapi.controllers.user.borrow_request;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request.BorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.services.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserBorrowRequestController
{
    private final BorrowRequestService borrowRequestService;
    @PostMapping("/borrow-request")
    public ResponseEntity<BorrowRequestResponse> createBorrowRequest(@RequestBody BorrowRequestRequest request) {

        BorrowRequestResponse borrowRequest = borrowRequestService.createBorrowRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRequest);
    }
}
