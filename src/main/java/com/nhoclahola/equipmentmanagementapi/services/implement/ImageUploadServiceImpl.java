package com.nhoclahola.equipmentmanagementapi.services.implement;

import com.nhoclahola.equipmentmanagementapi.exceptions.image_upload.ImageIsEmptyException;
import com.nhoclahola.equipmentmanagementapi.exceptions.image_upload.ImageIsNotSupportedException;
import com.nhoclahola.equipmentmanagementapi.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements FileUploadService
{
    private final Tika tika;

    @Override
    public String upload(String path, MultipartFile file) throws IOException
    {
        if (file.isEmpty())
            throw new ImageIsEmptyException();
        String fileType = tika.detect(file.getInputStream());

        if (fileType == null || !fileType.startsWith("image"))
            throw new ImageIsNotSupportedException();

        return this.createFile(path, file);
    }
}
