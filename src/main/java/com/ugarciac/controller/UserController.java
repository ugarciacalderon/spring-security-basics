package com.ugarciac.controller;

import com.ugarciac.dto.UserDto;
import com.ugarciac.mapper.UserMapper;
import com.ugarciac.model.Users;
import com.ugarciac.response.UserResponse;
import com.ugarciac.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto user) {
        Users savedUsers = new Users();
        ResponseEntity response = null;
        UserResponse userResponse = new UserResponse();
        try {
            savedUsers = usersService.save(user);
            if (null != savedUsers) {
                userResponse.setUsername(savedUsers.getUsername());
                userResponse.setEmail(savedUsers.getEmail());
                userResponse.setRoleName(savedUsers.getAuthorities().get(0).getRoleName());
                userResponse.setRolDescription(savedUsers.getAuthorities().get(0).getDescription());
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(userResponse);
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/user")
    public ResponseEntity<UserDto> getUserDetailsAfterLogin(Authentication authentication) {
        ResponseEntity response = null;
        try {
            List<Users> users = usersService.findByUsername(authentication.getName());
            UserDto userDto = UserMapper.userToUserDto(users.get(0), new UserDto());
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userDto);
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }
}
