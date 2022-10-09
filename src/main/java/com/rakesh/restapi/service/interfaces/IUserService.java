package com.rakesh.restapi.service.interfaces;

import com.rakesh.restapi.dto.UserDTO;
import org.hibernate.id.IntegralDataTypeHolder;

import java.io.File;

public interface IUserService {
    UserDTO createUser(UserDTO dto);
    File getFileByUserId(Integer userId);
}
