package com.example.ProfileService.profile;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByUserId(Long userId);

    Optional<City> findByName(String name);

    Optional<City> findByUserIdAndName(Long userId, String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM City c WHERE c.userId = :userId AND c.name = :name")
    int deleteByUserIdAndName(Long userId, String name);

}
