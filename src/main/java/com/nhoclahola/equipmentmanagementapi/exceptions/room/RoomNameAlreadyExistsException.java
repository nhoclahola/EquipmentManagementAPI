package com.nhoclahola.equipmentmanagementapi.exceptions.room;

public class RoomNameAlreadyExistsException extends RuntimeException
{
    public RoomNameAlreadyExistsException()
    {
        super("Tên phòng đã tồn tại");
    }
}
