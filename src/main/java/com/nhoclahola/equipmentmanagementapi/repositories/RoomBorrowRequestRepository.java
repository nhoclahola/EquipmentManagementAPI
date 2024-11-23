package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.UserInfoRoomBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBorrowRequestRepository extends JpaRepository<RoomBorrowRequest, Long>
{
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM RoomBorrowRequest r " +
            "WHERE r.room.roomId = :roomId AND r.status = 'APPROVED' AND r.isReturned = false")
    boolean isRoomBeingBorrowed(@Param("roomId") Long roomId);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.room_borrow_request.response.UserInfoRoomBorrowRequestResponse(" +
            "u.userId, " +
            "COALESCE(COUNT(br.id), 0), " +
            "COALESCE(SUM(CASE WHEN br.returnDate < CURRENT_DATE AND br.isReturned = false AND br.status = RequestStatus.APPROVED THEN 1 ELSE 0 END), 0), " +
            "COALESCE(MAX(br.borrowDate), null)) " +
            "FROM User u " +
            "LEFT JOIN RoomBorrowRequest br ON br.user.userId = u.userId " +
            "WHERE u.username = :username " +
            "GROUP BY u.userId")
    UserInfoRoomBorrowRequestResponse findUserInfoBorrowRequest(String username);

    @Query("SELECT COUNT(br) " +
            "FROM RoomBorrowRequest br " +
            "WHERE br.status = RequestStatus.PENDING")
    long countPendingRoomBorrowRequest();

    @Query("SELECT br " +
            "FROM RoomBorrowRequest br " +
            "WHERE br.user.username = :username")
    Page<RoomBorrowRequest> findUsersRoomBorrowRequests(@Param("username") String username, Pageable pageable);

    @Query("SELECT COUNT(DISTINCT r.room.roomId) " +
            "FROM RoomBorrowRequest r " +
            "WHERE r.status = 'APPROVED' AND r.isReturned = false")
    long countBorrowedRooms();
}
