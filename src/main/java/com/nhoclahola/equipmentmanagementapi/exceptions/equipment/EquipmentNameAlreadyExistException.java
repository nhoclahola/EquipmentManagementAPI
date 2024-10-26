package com.nhoclahola.equipmentmanagementapi.exceptions.equipment;

public class EquipmentNameAlreadyExistException extends RuntimeException
{
    public EquipmentNameAlreadyExistException()
    {
        super("The equipment name already exists");
    }
}
