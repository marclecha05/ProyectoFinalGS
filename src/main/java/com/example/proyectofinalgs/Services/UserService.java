package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Usuario saveUser(Usuario usuario) {
        return userRepository.save(usuario); // Guarda el usuario en la base de datos
    }

    public List<Usuario> findAll() {
        return userRepository.findAll(); // Recupera todos los usuarios
    }

    public void deleteById(int id) { // Cambié el tipo de `id` a Long, ajusta según tu entidad
        userRepository.deleteById(String.valueOf(id)); // Elimina al usuario por su ID
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Valida contraseñas
    }

    public Usuario findByUsername(String username) {
        return userRepository.findByUsername(username); // Ajusta según la lógica de tu repositorio
    }

    public Usuario findByEmail(String email) {
        return userRepository.findByEmail(email); // Encuentra usuario por correo
    }
}
