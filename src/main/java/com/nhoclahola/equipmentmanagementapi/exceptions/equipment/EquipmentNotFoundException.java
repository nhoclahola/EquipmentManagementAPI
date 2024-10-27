package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentNotFoundException extends RuntimeException
{
    public EquipmentNotFoundException()
    {
        super("Không tìm thấy thiết bị");
    }
}
