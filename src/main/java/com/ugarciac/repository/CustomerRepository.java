package com.ugarciac.repository;

import com.ugarciac.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public List<Customer> findByEmail(String email);
}
