package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.UserInfoBorrowRequestResponse;
import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long>
{
    @Query("SELECT COALESCE(SUM(br.quantity), 0) FROM BorrowRequest br " +
            "WHERE br.room.id = :roomId " +
            "AND br.equipment.equipmentId = :equipmentId " +
            "AND br.status = 'APPROVED' " +
            "AND br.isReturned = false")
    int findTotalBorrowedQuantityByRoomAndEquipment(@Param("roomId") Long roomId, @Param("equipmentId") Long equipmentId);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.borrow_request.response.UserInfoBorrowRequestResponse(" +
            "u.userId, " +
            "COALESCE(COUNT(br.id), 0), " +
            "COALESCE(SUM(CASE WHEN br.returnDate < CURRENT_DATE AND br.isReturned = false AND br.status = RequestStatus.APPROVED THEN 1 ELSE 0 END), 0), " +
            "COALESCE(MAX(br.borrowDate), null)) " +
            "FROM User u " +
            "LEFT JOIN BorrowRequest br ON br.user.userId = u.userId " +
            "WHERE u.username = :username " +
            "GROUP BY u.userId")
    UserInfoBorrowRequestResponse findUserInfoBorrowRequest(String username);

    @Query("SELECT COUNT(br) " +
            "FROM BorrowRequest br " +
            "WHERE br.status = RequestStatus.PENDING")
    long countPendingBorrowRequest();
}
