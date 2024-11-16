package com.nhoclahola.equipmentmanagementapi.exceptions.auth;

public class WrongRoleException extends RuntimeException
{
    public WrongRoleException(String message)
    {
        super(message);
    }
}
