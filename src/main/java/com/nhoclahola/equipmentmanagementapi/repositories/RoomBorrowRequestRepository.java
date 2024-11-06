package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.entities.RoomBorrowRequest;
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
}
