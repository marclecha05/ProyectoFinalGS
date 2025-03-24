package com.example.proyectofinalgs.Repositories;

import com.example.proyectofinalgs.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
