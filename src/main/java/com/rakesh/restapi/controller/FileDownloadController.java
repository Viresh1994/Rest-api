package com.rakesh.restapi.controller;

import com.rakesh.restapi.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
public class FileDownloadController {

    @Autowired private FileUtils fileUtils;
    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/resume-home")
    public String resumeHome() {
        return "resume.html";
    }

    @GetMapping("/resume")
    @ResponseBody
    public ResponseEntity<Resource> downloadResume() {
        InputStreamResource resource = null;
        try {
            HttpHeaders header = fileUtils.getHttpHeaders("Resume", "docx");
            File file= new File("C:\\Users\\Admin\\Downloads\\Resume.docx");
            return fileUtils.downloadFileAsResource(header, file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/resume2")
    @ResponseBody
    public ResponseEntity<Resource> downloadCoverLetter() {
        InputStreamResource resource = null;
        try {
            HttpHeaders header =  fileUtils.getHttpHeaders("Resume", "docx");
            File file= new File("C:\\Users\\Admin\\Downloads\\Resume.docx");
            return fileUtils.downloadFileAsResource(header, file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
