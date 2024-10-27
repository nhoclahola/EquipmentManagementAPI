package com.nhoclahola.equipmentmanagementapi.exceptions.borrow_request;

public class NotEnoughEquipmentAvailableException extends RuntimeException
{
    public NotEnoughEquipmentAvailableException()
    {
        super("Không đủ thiết bị khả dụng");
    }
}
