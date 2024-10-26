package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentNotExistsInRoomException extends RuntimeException
{
    public EquipmentNotExistsInRoomException()
    {
        super("The equipment not exists in the room");
    }
}
