package com.nhoclahola.equipmentmanagementapi.exceptions.handler;

import com.nhoclahola.equipmentmanagementapi.exceptions.PageNumberNotValidException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestHasBeenProcessedException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.BorrowRequestNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request.NotEnoughEquipmentAvailableException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentAlreadyExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNameAlreadyExistException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNotExistsInRoomException;
import com.nhoclahola.equipmentmanagementapi.exceptions.equipment.EquipmentNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.room.RoomNameAlreadyExistsException;
import com.nhoclahola.equipmentmanagementapi.exceptions.room.RoomNotFountException;
import com.nhoclahola.equipmentmanagementapi.exceptions.user.UserNotFoundException;
import com.nhoclahola.equipmentmanagementapi.exceptions.user.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(PageNumberNotValidException.class)
    public ResponseEntity<String> handlePageNumberNotValid(PageNumberNotValidException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409 Conflict
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409 Conflict
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EquipmentNameAlreadyExistException.class)
    public ResponseEntity<String> handleEquipmentNameAlreadyExists(EquipmentNameAlreadyExistException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409 Conflict
                .body(ex.getMessage());
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<String> handleEquipmentNotFound(EquipmentNotFoundException ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RoomNameAlreadyExistsException.class)
    public ResponseEntity<String> handleRoomNameAlreadyExists(RoomNameAlreadyExistsException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409 Conflict
                .body(ex.getMessage());
    }

    @ExceptionHandler(RoomNotFountException.class)
    public ResponseEntity<String> handleRoomNotFound(RoomNotFountException ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EquipmentAlreadyExistsInRoomException.class)
    public ResponseEntity<String> handleEquipmentAlreadyExistsInRoom(EquipmentAlreadyExistsInRoomException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409 Conflict
                .body(ex.getMessage());
    }

    @ExceptionHandler(EquipmentNotExistsInRoomException.class)
    public ResponseEntity<String> handleEquipmentNotExistsInRoom(EquipmentNotExistsInRoomException ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotEnoughEquipmentAvailableException.class)
    public ResponseEntity<String> handleNotEnoughEquipmentAvailable(NotEnoughEquipmentAvailableException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BorrowRequestNotFoundException.class)
    public ResponseEntity<String> handleBorrowRequestNotFound(BorrowRequestNotFoundException ex)
    {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BorrowRequestHasBeenProcessedException.class)
    public ResponseEntity<String> handleBorrowRequestHasBeenProcessed(BorrowRequestHasBeenProcessedException ex)
    {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
