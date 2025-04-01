package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }
    public Optional<Proveedor> findById(Integer id) {
        return proveedorRepository.findById(id);
    }
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
    public Proveedor deleteById(Integer id) {
        Proveedor proveedor = proveedorRepository.findById(id).get();
        proveedorRepository.delete(proveedor);
        return proveedor;
    }

}
