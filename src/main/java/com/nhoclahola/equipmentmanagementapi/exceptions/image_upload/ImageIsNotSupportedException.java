package com.nhoclahola.equipmentmanagementapi.exceptions.image_upload;

public class ImageIsNotSupportedException extends RuntimeException
{
    public ImageIsNotSupportedException()
    {
        super("Ảnh không được hỗ trợ");
    }
}
