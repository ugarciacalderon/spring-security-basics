package com.ugarciac.service;

import com.ugarciac.dto.UserDto;
import com.ugarciac.model.Users;

import java.util.List;

public interface UsersService {
    Users save(UserDto users);
    List<Users> findByUsername(String username);
}
