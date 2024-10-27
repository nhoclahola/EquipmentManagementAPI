package com.nhoclahola.equipmentmanagementapi.exceptions.image_upload;

public class ImageIsEmptyException extends RuntimeException
{
    public ImageIsEmptyException()
    {
        super("Ảnh rỗng");
    }
}
