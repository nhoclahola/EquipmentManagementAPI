package com.nhoclahola.equipmentmanagementapi.controllers.user;

import com.nhoclahola.equipmentmanagementapi.dto.room.response.RoomWithStatusResponse;
import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRoomController
{
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<Page<RoomWithStatusResponse>> getAllRooms(@RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.findAllRoomsPage(pageNumber - 1));
    }

    @GetMapping("/rooms/search")
    public ResponseEntity<Page<RoomWithStatusResponse>> searchRooms(@RequestParam("query") String query, @RequestParam("page") int pageNumber)
    {
        if (pageNumber <= 0)
            throw new PageNumberNotValidException();
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.searchRoomsPage(query, pageNumber - 1));
    }

    @GetMapping("/rooms/count")
    public ResponseEntity<Long> countAllRooms()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.count());
    }
}
