package com.nhoclahola.equipmentmanagementapi.exceptions.user;

public class UsernameAlreadyExistsException extends RuntimeException
{
    public UsernameAlreadyExistsException()
    {
        super("Username đã tồn tại");
    }
}
