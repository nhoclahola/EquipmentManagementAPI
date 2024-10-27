package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class BorrowRequestNotFoundException extends RuntimeException
{
    public BorrowRequestNotFoundException()
    {
        super("Phiếu đăng ký mượn không được tìm thấy");
    }
}
