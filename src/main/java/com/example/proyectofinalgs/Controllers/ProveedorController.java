package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Services.ProveedorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProveedorController {
    private final ProveedorService proveedorService;
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    @GetMapping("/proveedores")
    List<Proveedor> findAll() {
        return proveedorService.findAll();
    }
}
