package com.ga.todo.service;

import com.ga.todo.exception.ImageStorageException;
import com.ga.todo.exception.InvalidImageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageStorageService {
    private static final Map<String, String> ALLOWED_TYPES = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png"
    );

    private final Path categoryDirectory;

    public ImageStorageService(@Value("${app.upload.directory:uploads}") String uploadDirectory) {
        this.categoryDirectory = Path.of(uploadDirectory, "categories")
                .toAbsolutePath()
                .normalize();
    }

    public String store(MultipartFile image) {
        if (image.isEmpty()) {
            throw new InvalidImageException("Image is required");
        }

        String contentType = image.getContentType();
        if (contentType == null) {
            throw new InvalidImageException("Image content type is required");
        }

        String extension = ALLOWED_TYPES.get(contentType);
        if (extension == null) {
            throw new InvalidImageException("Only JPEG and PNG images are allowed");
        }

        String filename = UUID.randomUUID() + extension;
        Path destination = categoryDirectory.resolve(filename).normalize();

        if (!destination.startsWith(categoryDirectory)) {
            throw new InvalidImageException("Invalid image path");
        }

        try {
            Files.createDirectories(categoryDirectory);
            image.transferTo(destination);
            return "/uploads/categories/" + filename;
        } catch (IOException exception) {
            throw new ImageStorageException("Failed to save image", exception);
        }
    }
}
