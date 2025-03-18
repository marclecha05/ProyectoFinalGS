package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

}
