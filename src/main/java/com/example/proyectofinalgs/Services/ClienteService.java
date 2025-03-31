package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Entities.Usuario;
import com.example.proyectofinalgs.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }
    public List<Cliente> findByUsuario(Usuario usuario) {return clienteRepository.findByUsuario(usuario);  }
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Cliente deleteById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).get();
        clienteRepository.delete(cliente);
        return cliente;
    }
}
