package com.nhoclahola.equipmentmanagementapi.controllers.admin;

import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomCreateRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.request.RoomEditRequest;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomResponse;
import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse;
import com.nhoclahola.equipmentmanagementapi.entities.Room;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class RoomController
{
    private final RoomService roomService;

    @GetMapping("/rooms/count")
    public ResponseEntity<Long> getRoomsCount()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.count());
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomWithStatusResponse>> getAllRooms(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.findAllRooms(pageNumber - 1));
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody @Valid RoomCreateRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.createRoom(request));
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id)
    {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<RoomResponse> editRoom(@PathVariable Long id, @RequestBody @Valid RoomEditRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.editRoom(id, request));
    }

    @GetMapping("/rooms/search")
    public ResponseEntity<List<RoomWithStatusResponse>> searchRooms(@RequestParam("query") String query, @RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.searchRooms(query, pageNumber - 1));
    }

    @GetMapping("/rooms/search/count")
    public ResponseEntity<Long> getCountSearchRooms(@RequestParam("query") String query)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.countSearchRooms(query));
    }
}
