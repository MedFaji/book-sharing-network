package tech.medevs.book_network.book.file;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.medevs.book_network.book.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;

    public String storeFile(
            @NonNull MultipartFile sourceFile,
            @NonNull Book book,
            @NonNull Long userId
    ) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(
                sourceFile,
                fileUploadSubPath
        );
    }

    private String uploadFile(
            @NonNull MultipartFile sourceFile,
            @NonNull String fileUploadSubPath
    ) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFile = new File(finalUploadPath);
        if (!targetFile.exists()) {
            boolean folderCreated = targetFile.mkdirs();
            if (!folderCreated) {
                log.error("Failed to create folder for file upload: {}", finalUploadPath);
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator + System.currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File uploaded successfully: {}", targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("Failed to write file to disk: {}", targetFilePath);
            return null;
        }
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) return null;
        int lastIndexOfDot = originalFilename.lastIndexOf(".");
        if (lastIndexOfDot == -1) return null;
        return originalFilename.substring(lastIndexOfDot + 1).toLowerCase();
    }
}
