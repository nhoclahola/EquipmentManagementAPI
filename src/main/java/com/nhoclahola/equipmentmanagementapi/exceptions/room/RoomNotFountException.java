package com.nhoclahola.equipmentmanagementapi.exceptions.room;

public class RoomNotFountException extends RuntimeException
{
    public RoomNotFountException()
    {
        super("Room not found");
    }
}
