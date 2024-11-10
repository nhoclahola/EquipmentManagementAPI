package com.nhoclahola.equipmentmanagementapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public interface FileUploadService
{
    public static final String UPLOAD_DIR = "uploads/";
    public static final String ABSOLUTE_PATH = System.getProperty("user.dir") + "/" + UPLOAD_DIR;

    public abstract String upload(String path, MultipartFile file) throws IOException;

    // Utility methods
    public default String createFile(String path, MultipartFile file) throws IOException
    {
        String absolutePath = ABSOLUTE_PATH + path;
        // Create new unique file name
        String fileHexName = UUID.randomUUID().toString().replace("-", "");
        String extension = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = Paths.get(absolutePath, fileHexName + extension).toString();
        File targetFile = new File(filePath);

        // Create folder if it not exist
        if (!targetFile.getParentFile().exists())
            targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

        return (UPLOAD_DIR + path + fileHexName + extension).replaceAll("/+", "/");
    }

    public default boolean deleteFile(String path) throws IOException
    {
        String filePath = System.getProperty("user.dir") + "/" + path; // Absolute path of the file
        File file = new File(filePath);

        // Check if the file exists and delete it
        if (file.exists())
            return file.delete(); // Return true if deletion is successful
        return false;
    }
}
