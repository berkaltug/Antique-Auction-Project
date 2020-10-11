package com.scopic.antiqueauction.service.implementation;

import com.scopic.antiqueauction.exceptions.FileStorageException;
import com.scopic.antiqueauction.exceptions.MyFileNotFoundException;
import com.scopic.antiqueauction.service.FileStorageService;
import com.scopic.antiqueauction.utils.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final int BUFFER_SIZE=1024;
    private final Path fileStorageLocation;
    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties)  {
        this.fileStorageProperties=fileStorageProperties;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<String> storeZip(MultipartFile file) throws IOException,FileStorageException {
        List<String> pathNames = new LinkedList<String>();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
        ZipEntry entry = null;
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains("jpeg") || entry.getName().contains("jpg") || entry.getName().contains("png")) {
                    Path targetLocation = this.fileStorageLocation.resolve(entry.getName());
                    extractFile(zis, targetLocation.toString());
                    String path = fileStorageProperties.getUploadDir().replaceAll("src/main/resources/static", "");
                    pathNames.add(path + File.separator + entry.getName());
                } else {
                    throw new FileStorageException("Sorry! Zip file contains non-image format files.");
                }
            }
        } catch (IOException ex) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        return pathNames;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
