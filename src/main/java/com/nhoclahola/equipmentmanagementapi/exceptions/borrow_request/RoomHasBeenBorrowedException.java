package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class RoomHasBeenBorrowedException extends RuntimeException
{
    public RoomHasBeenBorrowedException()
    {
        super("Phòng đang được mượn");
    }
}
