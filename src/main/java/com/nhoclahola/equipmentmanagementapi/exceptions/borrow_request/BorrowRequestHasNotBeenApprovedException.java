package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class BorrowRequestHasNotBeenApprovedException extends RuntimeException
{
    public BorrowRequestHasNotBeenApprovedException()
    {
        super("Phiếu đăng ký mượn chưa được chấp nhận");
    }
}
