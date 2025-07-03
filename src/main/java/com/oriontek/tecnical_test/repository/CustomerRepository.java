package com.oriontek.tecnical_test.repository;

import com.oriontek.tecnical_test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Boolean existsByEmail(String email);
    public Boolean existsByPhone(String phone);
    public Customer findByEmail(String email);
    public Customer findByPhone(String phone);
}
