package com.rakesh.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
    private int id;
    private  String username;
    private String email;
    @JsonIgnore
    private MultipartFile file;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
