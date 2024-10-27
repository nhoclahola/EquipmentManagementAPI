package com.nhoclahola.equipmentmanagementapi.exceptions.user;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException()
    {
        super("Người dùng không được tìm thấy");
    }
}
