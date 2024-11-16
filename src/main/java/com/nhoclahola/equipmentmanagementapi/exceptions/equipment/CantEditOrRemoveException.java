package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class CantEditOrRemoveException extends RuntimeException
{
    public CantEditOrRemoveException(String message)
    {
        super(message);
    }
}
