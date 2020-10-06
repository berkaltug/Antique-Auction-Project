package com.scopic.antiqueauction.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    List<String> storeZip(MultipartFile file) throws IOException;
    Resource loadFileAsResource(String fileName);
}
