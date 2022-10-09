package com.rakesh.restapi.util;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class FileUtils {
    public ResponseEntity<Resource> downloadFileAsResource(HttpHeaders header, File file) throws FileNotFoundException {
        InputStreamResource resource;
        resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public HttpHeaders getHttpHeaders(String fileName, String fileType) {
        if(fileName.isEmpty() || fileType.isEmpty()) {
            throw new RuntimeException("File parameters empty");
        }
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+"." + fileType);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }
}
