package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentNotExistsInRoomException extends RuntimeException
{
    public EquipmentNotExistsInRoomException()
    {
        super("Thiết bị không tồn tại trong phòng");
    }
}
