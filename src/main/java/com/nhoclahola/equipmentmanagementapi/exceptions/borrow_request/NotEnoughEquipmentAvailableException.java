package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class NotEnoughEquipmentAvailableException extends RuntimeException
{
    public NotEnoughEquipmentAvailableException()
    {
        super("Not enough equipment available");
    }
}
