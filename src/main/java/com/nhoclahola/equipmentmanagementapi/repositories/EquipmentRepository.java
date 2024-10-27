package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithQuantity;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithRooms;
import com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms;
import com.nhoclahola.equipmentmanagementapi.entities.Equipment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>
{
    boolean existsByEquipmentName(String equipmentName);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithQuantity(e.equipmentId, e.equipmentName, e.imageUrl, " +
            "re.quantity, re.quantity - COALESCE(SUM(br.quantity), 0)) " +
            "FROM Equipment e " +
            "JOIN e.roomEquipments re " +
            "LEFT JOIN BorrowRequest br ON br.equipment.equipmentId = e.equipmentId " +
            "AND br.status = 'APPROVED' " +  // Only count on APPROVED request
            "AND br.isReturned = false " +  // And isn't be returned
            "WHERE re.room.id = :roomId " +
            "GROUP BY e.equipmentId, e.equipmentName, e.imageUrl, re.quantity")
    List<EquipmentWithQuantity> findEquipmentsWithQuantityByRoomId(@Param("roomId") Long roomId);

//    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms(e.equipmentId, e.equipmentName, e.imageUrl, SUM(re.quantity)) " +
//            "FROM Equipment e JOIN e.roomEquipments re GROUP BY e.equipmentId, e.equipmentName, e.imageUrl")
//    List<EquipmentWithTotalQuantityInAllRooms> findAllEquipmentsWithTotalQuantity(Pageable pageable);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.equipment.EquipmentWithTotalQuantityInAllRooms( " +
            "e.equipmentId, e.equipmentName, e.imageUrl, " +
            "SUM(re.quantity), " +
            "(SUM(re.quantity) - COALESCE((SELECT SUM(br.quantity) FROM BorrowRequest br " +
            "WHERE br.equipment.equipmentId = e.equipmentId AND br.status = 'APPROVED' AND br.isReturned = false), 0)) " +
            ") " +
            "FROM Equipment e " +
            "LEFT JOIN e.roomEquipments re " +      // To get all equipment which is not in any room
            "GROUP BY e.equipmentId, e.equipmentName, e.imageUrl")
    List<EquipmentWithTotalQuantityInAllRooms> findAllEquipmentsWithTotalQuantity(Pageable pageable);
}
