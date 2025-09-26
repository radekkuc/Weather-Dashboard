package com.example.ProfileService.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    public Optional<List<City>> findCityByUserId(Long userId);

    public Optional<City> findCityByName(String name);

//    @Modifying
//    public void deleteByName(String name);
}
