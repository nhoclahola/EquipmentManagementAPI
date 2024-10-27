package com.nhoclahola.equipmentmanagementapi.exceptions;

public class PageNumberNotValidException extends RuntimeException
{
    public PageNumberNotValidException()
    {
        super("Số trang không hợp lệ");
    }
}
