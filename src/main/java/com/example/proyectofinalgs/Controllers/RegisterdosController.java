package com.example.proyectofinalgs.Controllers;

import com.example.proyectofinalgs.Entities.Proveedor;
import com.example.proyectofinalgs.Repositories.ProviderRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RegisterdosController {
    @Autowired
    private ProviderRepository providerRepository;

    @PostMapping("/registerdosForm")
    @Transactional
    public void procesarSegundaEtapa(
            @RequestParam("businessName") String serviceName,
            @RequestParam("businessType") String serviceType,
            @RequestParam("location") String location,
            @RequestParam("shiftDuration") String durationTurn,
            HttpServletResponse response) throws IOException {

        System.out.println("Proveedor guardado");

        Proveedor proveedor = new Proveedor();
        proveedor.setServiceName(serviceName);
        proveedor.setServiceType(serviceType);
        proveedor.setLocation(location);
        proveedor.setDurationTurn(durationTurn);

        providerRepository.save(proveedor);

        response.sendRedirect("/registertres.html");
    }
}
