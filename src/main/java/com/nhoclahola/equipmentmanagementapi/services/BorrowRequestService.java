package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request.BorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface BorrowRequestService
{
    BorrowRequestResponse createBorrowRequest(BorrowRequestRequest request);

    BorrowRequestResponse approveBorrowRequest(Long requestId);

    BorrowRequestResponse rejectBorrowRequest(Long requestId);

    BorrowRequest findById(Long requestId);

    List<BorrowRequestResponse> findAllBorrowRequest(int pageNumber);
}
