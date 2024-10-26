package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class BorrowRequestHasBeenProcessedException extends RuntimeException
{
    public BorrowRequestHasBeenProcessedException()
    {
        super("This borrow request has been proccessed");
    }
}
