package com.nhoclahola.equipmentmanagementapi.controllers.user;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request.BorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.UserInfoBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserBorrowRequestController
{
    private final BorrowRequestService borrowRequestService;

    @GetMapping("/borrow-request")
    public ResponseEntity<Page<BorrowRequestResponse>> getUsersBorrowRequests(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.findUsersBorrowRequests(pageNumber - 1));
    }

    @PostMapping("/borrow-request")
    public ResponseEntity<BorrowRequestResponse> createBorrowRequest(@RequestBody BorrowRequestRequest request) {

        BorrowRequestResponse borrowRequest = borrowRequestService.createBorrowRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowRequest);
    }

    @GetMapping("/borrow-request/me")
    public ResponseEntity<UserInfoBorrowRequestResponse> findUserInfoBorrowRequest()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.findUserInfoBorrowRequest());
    }

    @GetMapping("/borrow-request/borrowed/count")
    public ResponseEntity<Long> getTotalBorrowedRoomEquipmentQuantity()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(borrowRequestService.findTotalBorrowedRoomEquipmentQuantity());
    }
}
