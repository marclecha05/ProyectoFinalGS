package com.example.proyectofinalgs.Repositories;

import com.example.proyectofinalgs.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, String> {
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);
}
