package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Repositories.ProveedorRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterdosController {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @PostMapping("/registerdosForm")
    public void procesarSegundaEtapa(
            @RequestParam("businessName") String serviceName,
            @RequestParam("businessType") String serviceType,
            @RequestParam("location") String location,
            @RequestParam("shiftDuration") String durationTurn,
            HttpServletResponse response,
            HttpSession session) throws IOException {

        // Recuperar el ID del proveedor desde la sesión
        Integer proveedorId = (Integer) session.getAttribute("proveedorId");
        if (proveedorId == null) {
            throw new RuntimeException("ID de proveedor no encontrado en la sesión");
        }

        // Buscar el proveedor y actualizarlo
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        proveedor.setServiceName(serviceName);
        proveedor.setServiceType(serviceType);
        proveedor.setLocation(location);
        proveedor.setDurationTurn(durationTurn);

        proveedorRepository.save(proveedor);

        response.sendRedirect("/registertres.html");
    }
}
