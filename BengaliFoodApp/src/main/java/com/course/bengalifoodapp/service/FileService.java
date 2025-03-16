package com.course.bengalifoodapp.service;

import com.course.bengalifoodapp.dto.FileData;
import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileData uploadFile(MultipartFile file, String path) throws IOException;

    void deleteFile(String path);

    Resource loadFile(String path);
}
