package com.oriontek.tecnical_test.repository;

import com.oriontek.tecnical_test.model.City;
import com.oriontek.tecnical_test.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Boolean existsByNameAndCountry(String name, Country country);
    City findByNameAndCountry(String name, Country country);
}
