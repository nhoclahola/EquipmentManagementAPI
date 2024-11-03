package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity;
import com.nhoclahola.equipmentmanagementapi.entities.RoomEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomEquipmentRepository extends JpaRepository<RoomEquipment, Long>
{
    List<RoomEquipment> findByRoomRoomId(Long roomId);

    @Query("""
    SELECT new com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity(
        re.id,
        re.room,
        re.equipment,
        re.quantity,
        re.quantity - COALESCE((
            SELECT SUM(br.quantity) 
            FROM BorrowRequest br 
            WHERE br.equipment = re.equipment 
            AND br.room = re.room 
            AND br.status = 'APPROVED' 
            AND br.isReturned = false
        ), 0)
    )
    FROM RoomEquipment re
    WHERE re.room.roomId = :roomId
""")
    List<RoomEquipmentWithRemainQuantity> findRoomEquipmentsWithRemainQuantityByRoomId(@Param("roomId") Long roomId);

    boolean existsByRoomRoomIdAndEquipmentEquipmentId(Long roomId, Long equipmentId);

    Optional<RoomEquipment> findByRoomRoomIdAndEquipmentEquipmentId(Long roomId, Long equipmentId);

    @Query("SELECT COALESCE(SUM(re.quantity), 0) FROM RoomEquipment re " +
            "WHERE re.room.id = :roomId AND re.equipment.equipmentId = :equipmentId")
    int findTotalQuantityByRoomAndEquipment(@Param("roomId") Long roomId, @Param("equipmentId") Long equipmentId);

    @Query("""
                SELECT new com.nhoclahola.equipmentmanagementapi.dto.room_equipment.RoomEquipmentWithRemainQuantity(
                    re.id,
                    re.room,
                    re.equipment,
                    re.quantity,
                    re.quantity - COALESCE((
                        SELECT SUM(br.quantity) 
                        FROM BorrowRequest br 
                        WHERE br.equipment = re.equipment 
                        AND br.room = re.room 
                        AND br.status = 'APPROVED' 
                        AND br.isReturned = false
                    ), 0)
                )
                FROM RoomEquipment re
                WHERE re.room.roomId = :roomId 
                AND re.equipment.equipmentId = :equipmentId
            """)
    Optional<RoomEquipmentWithRemainQuantity> findRoomEquipmentWithRemainQuantityByRoomIdAndEquipmentId(
            @Param("roomId") Long roomId,
            @Param("equipmentId") Long equipmentId
    );
}
