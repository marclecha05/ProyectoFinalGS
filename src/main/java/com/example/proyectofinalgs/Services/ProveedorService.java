package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    private ProviderRepository providerRepository;

    public List<Proveedor> findAll() {
        return providerRepository.findAll();
    }
    public Optional<Proveedor> findById(Integer id) {
        return providerRepository.findById(id);
    }
    public Proveedor save(Proveedor proveedor) {
        return providerRepository.save(proveedor);
    }
    public Proveedor deleteById(Integer id) {
        Proveedor proveedor = providerRepository.findById(id).get();
        providerRepository.delete(proveedor);
        return proveedor;
    }

}
