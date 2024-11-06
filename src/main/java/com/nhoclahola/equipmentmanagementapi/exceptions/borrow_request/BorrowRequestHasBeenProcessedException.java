package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class BorrowRequestHasBeenProcessedException extends RuntimeException
{
    public BorrowRequestHasBeenProcessedException()
    {
        super("Phiếu đăng ký mượn đã được xử lý");
    }
}
