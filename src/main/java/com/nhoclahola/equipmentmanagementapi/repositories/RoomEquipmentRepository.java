package com.nhoclahola.equipmentmanagementapi.repositories;

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

    boolean existsByRoomRoomIdAndEquipmentEquipmentId(Long roomId, Long equipmentId);

    Optional<RoomEquipment> findByRoomRoomIdAndEquipmentEquipmentId(Long roomId, Long equipmentId);

    @Query("SELECT COALESCE(SUM(re.quantity), 0) FROM RoomEquipment re " +
            "WHERE re.room.id = :roomId AND re.equipment.equipmentId = :equipmentId")
    int findTotalQuantityByRoomAndEquipment(@Param("roomId") Long roomId, @Param("equipmentId") Long equipmentId);
}
