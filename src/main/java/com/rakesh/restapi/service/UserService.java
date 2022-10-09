package com.rakesh.restapi.service;

import com.rakesh.restapi.dao.ResumeFileDao;
import com.rakesh.restapi.dao.UserDao;
import com.rakesh.restapi.dto.UserDTO;
import com.rakesh.restapi.repo.IResumeRepo;
import com.rakesh.restapi.repo.IUserRepo;
import com.rakesh.restapi.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepo userRepo;

    @Autowired private IResumeRepo iResumeRepo;
    @Override
    public UserDTO createUser(UserDTO dto) {
        UserDao user = new UserDao();
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmail());
        if(!dto.getFile().isEmpty()) {
            //save multipart file
            ResumeFileDao fileDao = new ResumeFileDao();
            try {
                fileDao.setName(StringUtils.cleanPath(dto.getFile().getOriginalFilename()));
                fileDao.setData(dto.getFile().getBytes());
                ResumeFileDao updatedFile = iResumeRepo.save(fileDao);
                user.setResumeFileDao(updatedFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        UserDao savedUser = userRepo.save(user);
        if(savedUser.getId() != null) {
            dto.setId(savedUser.getId());
        }
        return dto;
    }

    @Override
    public File getFileByUserId(Integer userId) {
        Optional<UserDao> userDao = userRepo.findById(userId);
        if(userDao.isPresent()){
            File f =new File("D:\\java-2020\\rest-api\\Resume.docx");
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(f);
                outputStream.write(userDao.get().getResumeFileDao().getData());
                return f;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return null;
    }
}
