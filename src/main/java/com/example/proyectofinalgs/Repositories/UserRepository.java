package com.example.proyectofinalgs.Repositories;

import com.example.proyectofinalgs.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findUsersByEmail(String email);
}
