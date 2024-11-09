package com.nhoclahola.equipmentmanagementapi.repositories;

import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>
{
    boolean existsByRoomName(String roomName);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse(r.roomId, r.roomName, " +
            "CASE WHEN EXISTS (" +
            "    SELECT 1 FROM RoomBorrowRequest rb " +
            "    WHERE rb.room = r AND rb.status = APPROVED AND rb.isReturned = false" +
            ") THEN true ELSE false END" +
            ") " +
            "FROM Room r")
    List<RoomWithStatusResponse> findAllRoomsWithBorrowedStatus(Pageable pageable);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse(r.roomId, r.roomName, " +
            "CASE WHEN EXISTS (" +
            "    SELECT 1 FROM RoomBorrowRequest rb " +
            "    WHERE rb.room = r AND rb.status = APPROVED AND rb.isReturned = false" +
            ") THEN true ELSE false END" +
            ") " +
            "FROM Room r")
    Page<RoomWithStatusResponse> findAllRoomsWithBorrowedStatusPage(Pageable pageable);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse(r.roomId, r.roomName, " +
            "CASE WHEN EXISTS (" +
            "    SELECT 1 FROM RoomBorrowRequest rb " +
            "    WHERE rb.room = r AND rb.status = APPROVED AND rb.isReturned = false" +
            ") THEN true ELSE false END" +
            ") " +
            "FROM Room r " +
            "WHERE r.roomName LIKE %:query%")
    List<RoomWithStatusResponse> searchAllRoomsWithBorrowedStatus(@Param("query") String query, Pageable pageable);

    @Query("SELECT new com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse(r.roomId, r.roomName, " +
            "CASE WHEN EXISTS (" +
            "    SELECT 1 FROM RoomBorrowRequest rb " +
            "    WHERE rb.room = r AND rb.status = APPROVED AND rb.isReturned = false" +
            ") THEN true ELSE false END" +
            ") " +
            "FROM Room r " +
            "WHERE r.roomName LIKE %:query%")
    Page<RoomWithStatusResponse> searchAllRoomsWithBorrowedStatusPage(@Param("query") String query, Pageable pageable);

    @Query("SELECT COUNT(r) " +
            "FROM Room r " +
            "WHERE r.roomName LIKE %:query%")
    long countSearchRooms(@Param("query") String query);
}
