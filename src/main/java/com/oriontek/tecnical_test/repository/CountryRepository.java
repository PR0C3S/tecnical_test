package com.oriontek.tecnical_test.repository;

import com.oriontek.tecnical_test.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    public Boolean existsByName(String name);
    public Country findByName(String name);
}
