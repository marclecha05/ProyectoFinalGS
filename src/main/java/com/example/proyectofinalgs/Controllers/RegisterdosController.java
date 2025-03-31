package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Repositories.ProviderRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class RegisterdosController {
    @Autowired
    private ProviderRepository providerRepository;

    @PostMapping("/registerdosForm")
    public void procesarSegundaEtapa(@RequestParam String respuesta,
        @RequestParam("businessName") String serviceName,
        @RequestParam("businessType") String serviceType,
        @RequestParam("location") String location,
        @RequestParam("shiftDuration") String durationTurn,
        RedirectAttributes redirectAttributes, HttpServletResponse response ) throws IOException {

            // Crear un nuevo objeto Provider y asignar los valores del formulario
            Proveedor proveedor = new Proveedor();
            proveedor.setServiceName(serviceName);
            proveedor.setServiceType(serviceType);
            proveedor.setLocation(location);
            proveedor.setDurationTurn(durationTurn);

            // Guardar en la base de datos
            providerRepository.save(proveedor);

            response.sendRedirect("/registertres.html");
        }
}
