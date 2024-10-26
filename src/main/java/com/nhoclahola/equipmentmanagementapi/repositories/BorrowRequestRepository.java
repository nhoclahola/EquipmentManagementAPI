package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.entities.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long>
{
    @Query("SELECT COALESCE(SUM(br.quantity), 0) FROM BorrowRequest br " +
            "WHERE br.room.id = :roomId " +
            "AND br.equipment.equipmentId = :equipmentId " +
            "AND br.status = 'APPROVED' " +
            "AND br.isReturned = false")
    int findTotalBorrowedQuantityByRoomAndEquipment(@Param("roomId") Long roomId, @Param("equipmentId") Long equipmentId);
}
