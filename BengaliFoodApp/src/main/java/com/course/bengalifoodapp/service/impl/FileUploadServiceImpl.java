package com.course.bengalifoodapp.service.impl;

import com.course.bengalifoodapp.dto.FileData;
import com.course.bengalifoodapp.exception.FilePathNotFound;
import com.course.bengalifoodapp.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileService {

    @Override
    public FileData uploadFile(MultipartFile file, String path) {
        if (path.isBlank()){
            throw new FilePathNotFound("path is blank");
        }
        Path filePath = Paths.get(path.substring(0,path.lastIndexOf("/")+1));
        log.info(filePath.toString());
        return null;
    }

    @Override
    public void deleteFile(String path) {

    }

    @Override
    public Resource loadFile(String path) {
        return null;
    }
}
