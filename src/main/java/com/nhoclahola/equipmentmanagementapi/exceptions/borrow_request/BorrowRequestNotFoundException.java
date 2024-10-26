package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class BorrowRequestNotFoundException extends RuntimeException
{
    public BorrowRequestNotFoundException()
    {
        super("Borrow Request not found");
    }
}
