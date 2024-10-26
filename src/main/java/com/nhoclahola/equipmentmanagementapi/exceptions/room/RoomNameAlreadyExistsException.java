package com.nhoclahola.equipmentmanagementapi.exceptions.room;

public class RoomNameAlreadyExistsException extends RuntimeException
{
    public RoomNameAlreadyExistsException()
    {
        super("Room name already exists");
    }
}
