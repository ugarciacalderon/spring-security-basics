package com.ugarciac.mapper;

import com.ugarciac.dto.AuthoritiesDto;
import com.ugarciac.dto.UserDto;
import com.ugarciac.model.Authorities;
import com.ugarciac.model.Users;

public class UserMapper {

    public static UserDto userToUserDto(Users users, UserDto userDto) {
        userDto.setUsername(userDto.getUsername());
        userDto.setPassword(userDto.getPassword());
        userDto.setEmail(users.getEmail());
        return userDto;
    }

    public static Users userDtoToUser(UserDto userDto, Users users) {
        users.setUsername(userDto.getUsername());
        users.setEmail(userDto.getEmail());
        return users;
    }

    public static Authorities authDtoToAuthorities(AuthoritiesDto authoritiesDto, Authorities authorities) {
        authorities.setRoleName(authoritiesDto.getRoleName());
        authorities.setDescription(authoritiesDto.getDescription());
        return authorities;
    }
    public static AuthoritiesDto authoritiesToAuthoritiesDto(Authorities authorities, AuthoritiesDto authoritiesDto) {
        authoritiesDto.setRoleName(authorities.getRoleName());
        authoritiesDto.setDescription(authorities.getDescription());
        return authoritiesDto;
    }
}
