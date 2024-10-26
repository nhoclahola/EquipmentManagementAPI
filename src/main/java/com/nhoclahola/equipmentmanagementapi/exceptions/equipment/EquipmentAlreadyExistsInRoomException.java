package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentAlreadyExistsInRoomException extends RuntimeException
{
    public EquipmentAlreadyExistsInRoomException()
    {
        super("The equipment already exists in room");
    }
}
