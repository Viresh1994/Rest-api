package com.rakesh.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rakesh.restapi.dto.UserDTO;
import com.rakesh.restapi.service.interfaces.IUserService;
import com.rakesh.restapi.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired private IUserService userService;
    @Autowired private FileUtils fileUtils;
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam(name= "user") String userDtoParam,
                                              @RequestParam(name="resume", required = false) MultipartFile file) throws JsonProcessingException {
        UserDTO userDTO = parseUserFromParam(userDtoParam, file);
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    private UserDTO parseUserFromParam(String userDtoParam, MultipartFile file) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        UserDTO userDTO = mapper.readValue(userDtoParam, UserDTO.class);
        if(!file.isEmpty()){
            userDTO.setFile(file);
        }
        return userDTO;
    }

    @GetMapping("/resume/{userId}")
    @ResponseBody
    public ResponseEntity<Resource> downloadResume(@PathVariable("userId") Integer userId) {
        InputStreamResource resource = null;
        try {
            HttpHeaders header = fileUtils.getHttpHeaders("Resume", "docx");
            return fileUtils.downloadFileAsResource(header, userService.getFileByUserId(userId));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
