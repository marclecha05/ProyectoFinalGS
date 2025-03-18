package com.example.proyectofinalgs.Services;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Entities.Reserva;
import com.example.proyectofinalgs.Repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }
    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }
    public List<Reserva> findByCliente(Cliente cliente) {
        Reserva reserva = new Reserva();
        if (reserva.getCliente_id()==cliente.getId()) {
            return reservaRepository.findAll();
        }
        else return null;
    }
    public Reserva deleteById(Integer id) {
        Reserva reserva = reservaRepository.findById(id).get();
        reservaRepository.delete(reserva);
        return reserva;
    }
}
