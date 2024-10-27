package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentNameAlreadyExistException extends RuntimeException
{
    public EquipmentNameAlreadyExistException()
    {
        super("Tên thiết bị đã tồn tại");
    }
}
