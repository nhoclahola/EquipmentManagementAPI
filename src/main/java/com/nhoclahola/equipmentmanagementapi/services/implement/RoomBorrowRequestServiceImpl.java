package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.request.RoomBorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.RoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.UserInfoRoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RequestStatus;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestHasBeenProcessedException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestHasNotBeenApprovedException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.RoomHasBeenBorrowedException;
import com.nhoclahola.equipmentmanagementapi.mapper.RoomBorrowRequestMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.RoomBorrowRequestRepository;
import com.nhoclahola.equipmentmanagementapi.services.RoomBorrowRequestService;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import com.nhoclahola.equipmentmanagementapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomBorrowRequestServiceImpl implements RoomBorrowRequestService
{
    private final RoomBorrowRequestRepository roomBorrowRequestRepository;
    private final RoomBorrowRequestMapper roomBorrowRequestMapper;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public RoomBorrowRequestResponse createRoomBorrowRequest(RoomBorrowRequestRequest request)
    {
        // Check user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        // Kiểm tra phòng có đang được mượn không?
        boolean isBorrowed = roomBorrowRequestRepository.isRoomBeingBorrowed(request.getRoomId());
        if (isBorrowed)
            throw new RoomHasBeenBorrowedException();
        RoomBorrowRequest borrowRequest = RoomBorrowRequest.builder()
                .user(user)
                .room(roomService.findById(request.getRoomId())) // Không cần tìm lại Room vì roomId đã có
                .requestDate(LocalDate.now())  // Ngày tạo yêu cầu
                .returnDate(request.getReturnDate())  // Ngày dự kiến trả
                .isReturned(false)
                .status(RequestStatus.PENDING)  // Trạng thái ban đầu là PENDING
                .build();
        return roomBorrowRequestMapper.toRoomBorrowRequestResponse(roomBorrowRequestRepository.save(borrowRequest));
    }

    @Override
    public RoomBorrowRequestResponse approveBorrowRequest(Long requestId)
    {
        RoomBorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.PENDING))
            throw new BorrowRequestHasBeenProcessedException();
        boolean isBorrowed = roomBorrowRequestRepository.isRoomBeingBorrowed(borrowRequest.getRoom().getRoomId());
        if (isBorrowed)
            throw new RuntimeException("Phòng đang được mượn");
        else
        {
            borrowRequest.setStatus(RequestStatus.APPROVED);
            borrowRequest.setBorrowDate(LocalDate.now());
            return roomBorrowRequestMapper.toRoomBorrowRequestResponse(roomBorrowRequestRepository.save(borrowRequest));
        }
    }

    @Override
    public RoomBorrowRequestResponse rejectBorrowRequest(Long requestId)
    {
        RoomBorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.PENDING))
            throw new BorrowRequestHasBeenProcessedException();

        borrowRequest.setStatus(RequestStatus.REJECTED);
        return roomBorrowRequestMapper.toRoomBorrowRequestResponse(roomBorrowRequestRepository.save(borrowRequest));
    }

    @Override
    public RoomBorrowRequestResponse markAsReturnedBorrowRequest(Long requestId)
    {
        RoomBorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.APPROVED))
            throw new BorrowRequestHasNotBeenApprovedException();
        borrowRequest.setReturned(true);
        return roomBorrowRequestMapper.toRoomBorrowRequestResponse(roomBorrowRequestRepository.save(borrowRequest));
    }

    @Override
    public RoomBorrowRequest findById(Long requestId)
    {
        return roomBorrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new BorrowRequestNotFoundException());
    }

    @Override
    public long count()
    {
        return roomBorrowRequestRepository.count();
    }

    @Override
    public List<RoomBorrowRequestResponse> findAllBorrowRequest(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        List<RoomBorrowRequest> borrowRequestList = roomBorrowRequestRepository.findAll(pageable).stream().toList();
        return roomBorrowRequestMapper.toRoomBorrowRequestResponseList(borrowRequestList);
    }

    @Override
    public List<RoomBorrowRequestResponse> findAllLatestBorrowRequest(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("id").descending());
        List<RoomBorrowRequest> borrowRequestList = roomBorrowRequestRepository.findAll(pageable).stream().toList();
        return roomBorrowRequestMapper.toRoomBorrowRequestResponseList(borrowRequestList);
    }

    @Override
    public UserInfoRoomBorrowRequestResponse findUserInfoBorrowRequest()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return roomBorrowRequestRepository.findUserInfoBorrowRequest(username);
    }

    @Override
    public long countPendingBorrowRequest()
    {
        return roomBorrowRequestRepository.countPendingRoomBorrowRequest();
    }

    @Override
    public Page<RoomBorrowRequestResponse> findUsersRoomBorrowRequests(int pageNumber)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("id").descending());
        Page<RoomBorrowRequest> roomBorrowRequests = roomBorrowRequestRepository.findUsersRoomBorrowRequests(username, pageable);
        return roomBorrowRequestMapper.toPageRoomBorrowRequestResponse(roomBorrowRequests);
    }
}
