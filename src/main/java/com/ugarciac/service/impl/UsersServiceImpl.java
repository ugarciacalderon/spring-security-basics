package com.ugarciac.service.impl;

import com.ugarciac.dto.UserDto;
import com.ugarciac.mapper.UserMapper;
import com.ugarciac.model.Authorities;
import com.ugarciac.model.Users;
import com.ugarciac.repository.AuthoritiesRepository;
import com.ugarciac.repository.UsersRepository;
import com.ugarciac.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users save(UserDto users) {
        try {
            String hashPassword = passwordEncoder.encode(users.getPassword());
            Users userToSave = UserMapper.userDtoToUser(users, new Users());
            userToSave.setCreatedAt(new Date());
            userToSave.setUpdatedAt(new Date());
            userToSave.setEmail(users.getEmail());
            userToSave.setEnabled(true);
            userToSave.setPassword(hashPassword);
            userToSave.setMobileNumber(users.getMobileNumber());

            usersRepository.save(userToSave);
            List<Users> userSaved = usersRepository.findByEmail(users.getEmail());

            // save authorities
            Authorities auth = UserMapper.authDtoToAuthorities(users.getAuthoritiesDto(), new Authorities());
            auth.setUsers(userSaved.get(0));
            auth.setCreatedAt(new Date());
            auth.setUpdatedAt(new Date());
            auth.setEnabled(true);
            authoritiesRepository.save(auth);
            List<Authorities> authSaved = authoritiesRepository.findByRoleName(auth.getRoleName());
            userSaved.get(0).setAuthorities(authSaved);
            return userSaved.get(0);
        } catch (Exception e) {
            log.error("Error al crear el usuario: {}", users.getUsername());
        }
        return null;
    }

    @Override
    public List<Users> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
