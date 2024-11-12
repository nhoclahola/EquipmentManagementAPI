package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.request.BorrowRequestRequest;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.BorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.UserInfoBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import com.nhoclahola.equipmentmanagementapi.entities.RequestStatus;
import com.nhoclahola.equipmentmanagementapi.entities.User;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestHasBeenProcessedException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestHasNotBeenApprovedException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.NotEnoughEquipmentAvailableException;
import com.nhoclahola.equipmentmanagementapi.mapper.BorrowRequestMapper;
import com.nhoclahola.equipmentmanagementapi.repositories.BorrowRequestRepository;
import com.nhoclahola.equipmentmanagementapi.services.*;
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
public class BorrowRequestServiceImpl implements BorrowRequestService
{
    private final BorrowRequestRepository borrowRequestRepository;
    private final BorrowRequestMapper borrowRequestMapper;
    private final UserService userService;
    private final EquipmentService equipmentService;
    private final RoomService roomService;
    private final RoomEquipmentService roomEquipmentService;

    @Override
    public BorrowRequestResponse createBorrowRequest(BorrowRequestRequest request)
    {
        // Check user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        Equipment equipment = equipmentService.findById(request.getEquipmentId());
        // Tính tổng số lượng thiết bị trong phòng từ RoomEquipment
        int totalQuantityInRoom = roomEquipmentService
                .findTotalQuantityByRoomAndEquipment(request.getRoomId(), request.getEquipmentId());

        // Tính tổng số lượng thiết bị đã được mượn nhưng chưa trả (APPROVED & chưa trả)
        int totalBorrowedQuantity = borrowRequestRepository
                .findTotalBorrowedQuantityByRoomAndEquipment(request.getRoomId(), request.getEquipmentId());
        // Tính số lượng còn lại
        int availableQuantity = totalQuantityInRoom - totalBorrowedQuantity;
        // Kiểm tra xem số lượng yêu cầu có vượt quá số lượng còn lại không
        if (request.getQuantity() > availableQuantity)
            throw new NotEnoughEquipmentAvailableException();
        // Nếu còn đủ số lượng, tạo BorrowRequest mới
        BorrowRequest borrowRequest = BorrowRequest.builder()
                .equipment(equipment)
                .user(user)
                .room(roomService.findById(request.getRoomId())) // Không cần tìm lại Room vì roomId đã có
                .quantity(request.getQuantity())
                .requestDate(LocalDate.now())  // Ngày tạo yêu cầu
                .returnDate(request.getReturnDate())  // Ngày dự kiến trả
                .isReturned(false)
                .status(RequestStatus.PENDING)  // Trạng thái ban đầu là PENDING
                .build();
        return borrowRequestMapper.toBorrowRequestResponse(borrowRequestRepository.save(borrowRequest));
    }

    @Override
    public BorrowRequestResponse approveBorrowRequest(Long requestId)
    {
        BorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.PENDING))
            throw new BorrowRequestHasBeenProcessedException();
        // Tính tổng số lượng thiết bị trong phòng từ RoomEquipment
        int totalQuantityInRoom = roomEquipmentService
                .findTotalQuantityByRoomAndEquipment(borrowRequest.getRoom().getRoomId(), borrowRequest.getEquipment().getEquipmentId());
        // Tính tổng số lượng thiết bị đã được mượn nhưng chưa trả (APPROVED & chưa trả)
        int totalBorrowedQuantity = borrowRequestRepository
                .findTotalBorrowedQuantityByRoomAndEquipment(borrowRequest.getRoom().getRoomId(), borrowRequest.getEquipment().getEquipmentId());
        // Tính số lượng còn lại
        int availableQuantity = totalQuantityInRoom - totalBorrowedQuantity;
        if (borrowRequest.getQuantity() > availableQuantity)
            throw new NotEnoughEquipmentAvailableException();
        else
        {
            borrowRequest.setStatus(RequestStatus.APPROVED);
            borrowRequest.setBorrowDate(LocalDate.now());
            return borrowRequestMapper.toBorrowRequestResponse(borrowRequestRepository.save(borrowRequest));
        }
    }

    @Override
    public BorrowRequestResponse rejectBorrowRequest(Long requestId)
    {
        BorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.PENDING))
            throw new BorrowRequestHasBeenProcessedException();
        borrowRequest.setStatus(RequestStatus.REJECTED);
        return borrowRequestMapper.toBorrowRequestResponse(borrowRequestRepository.save(borrowRequest));
    }

    @Override
    public BorrowRequestResponse markAsReturnedBorrowRequest(Long requestId)
    {
        BorrowRequest borrowRequest = this.findById(requestId);
        if (!borrowRequest.getStatus().equals(RequestStatus.APPROVED))
            throw new BorrowRequestHasNotBeenApprovedException();
        borrowRequest.setReturned(true);
        return borrowRequestMapper.toBorrowRequestResponse(borrowRequestRepository.save(borrowRequest));
    }

    @Override
    public BorrowRequest findById(Long requestId)
    {
        return borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new BorrowRequestNotFoundException());
    }

    @Override
    public long count()
    {
        return borrowRequestRepository.count();
    }

    @Override
    public List<BorrowRequestResponse> findAllBorrowRequest(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        List<BorrowRequest> borrowRequestList = borrowRequestRepository.findAll(pageable).stream().toList();
        return borrowRequestMapper.toBorrowRequestResponseList(borrowRequestList);
    }

    @Override
    public List<BorrowRequestResponse> findAllLatestBorrowRequest(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("id").descending());
        List<BorrowRequest> borrowRequestList = borrowRequestRepository.findAll(pageable).stream().toList();
        return borrowRequestMapper.toBorrowRequestResponseList(borrowRequestList);
    }

    @Override
    public UserInfoBorrowRequestResponse findUserInfoBorrowRequest()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return borrowRequestRepository.findUserInfoBorrowRequest(username);
    }

    @Override
    public long countPendingBorrowRequest()
    {
        return borrowRequestRepository.countPendingBorrowRequest();
    }

    @Override
    public Page<BorrowRequestResponse> findUsersBorrowRequests(int pageNumber)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("id").descending());
        Page<BorrowRequest> borrowRequests = borrowRequestRepository.findUsersBorrowRequests(username, pageable);
        return borrowRequestMapper.toPageBorrowRequestResponse(borrowRequests);
    }
}
