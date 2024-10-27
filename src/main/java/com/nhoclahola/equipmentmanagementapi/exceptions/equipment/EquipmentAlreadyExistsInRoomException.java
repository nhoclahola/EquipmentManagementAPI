package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentAlreadyExistsInRoomException extends RuntimeException
{
    public EquipmentAlreadyExistsInRoomException()
    {
        super("Thiết bị đã tồn tại sẵn trong phòng");
    }
}
