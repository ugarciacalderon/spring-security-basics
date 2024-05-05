package com.ugarciac.repository;

import com.ugarciac.model.Authorities;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {
    public List<Authorities> findByRoleName(String name);
}
