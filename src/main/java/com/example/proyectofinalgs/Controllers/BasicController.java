package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Cliente;
import com.example.proyectofinalgs.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class BasicController {

    private final Logger log = Logger.getLogger(BasicController.class.getName());

    @Autowired
    private ClienteService clienteService;
    @Autowired


    @GetMapping("/a")
    public List<Cliente> findAll() {
        log.info("Ejecuta findAll de Clientes sin autorizado");
        return clienteService.findAll();
    }
    @GetMapping("/b")
    public List<Cliente> findAllCliente() {
        log.info("Ejecuta findAll de Clientes con autorizado");
        return clienteService.findAll();
    }

}
