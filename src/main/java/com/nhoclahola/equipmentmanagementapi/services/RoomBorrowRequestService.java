package com.nhoclahola.equipmentmanagementapi.services;

import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.request.RoomBorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.UserInfoRoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomBorrowRequestService
{

    RoomBorrowRequestResponse createRoomBorrowRequest(RoomBorrowRequestRequest request);

    RoomBorrowRequestResponse approveBorrowRequest(Long requestId);

    RoomBorrowRequestResponse rejectBorrowRequest(Long requestId);

    RoomBorrowRequestResponse markAsReturnedBorrowRequest(Long requestId);

    RoomBorrowRequest findById(Long requestId);

    long count();

    List<RoomBorrowRequestResponse> findAllBorrowRequest(int pageNumber);

    List<RoomBorrowRequestResponse> findAllLatestBorrowRequest(int pageNumber);

    UserInfoRoomBorrowRequestResponse findUserInfoBorrowRequest();

    long countPendingBorrowRequest();

    Page<RoomBorrowRequestResponse> findUsersRoomBorrowRequests(int pageNumber);

    long countBorrowedRooms();
}
