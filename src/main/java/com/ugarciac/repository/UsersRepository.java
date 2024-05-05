package com.ugarciac.repository;

import com.ugarciac.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users, Long> {
    List<Users> findByEmail(String email);
    List<Users> findByUsername(String username);
}
