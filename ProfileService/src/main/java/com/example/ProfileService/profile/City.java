package com.example.ProfileService.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    private Long id;

    private Long userId;
    private String name;

}
