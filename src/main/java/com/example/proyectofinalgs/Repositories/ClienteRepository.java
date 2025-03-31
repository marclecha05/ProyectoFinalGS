package com.example.proyectofinalgs.Repositories;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByUsuario(Usuario usuario);
}
