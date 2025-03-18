package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Provider;
import com.example.proyectofinalgs.Repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService  {
    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> findAll() {
        return providerRepository.findAll();
    }
    public Optional<Provider> findById(Integer id) {
        return providerRepository.findById(id);
    }
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }
    public Provider deleteById(Integer id) {
        Provider provider = providerRepository.findById(id).get();
        providerRepository.delete(provider);
        return provider;
    }

}
