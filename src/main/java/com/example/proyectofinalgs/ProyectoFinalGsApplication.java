package com.example.proyectofinalgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@SpringBootApplication
@EnableScheduling
@EnableWebMvc
public class ProyectoFinalGsApplication /*extends SpringBootServletInitializer */{

 /*   @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProyectoFinalGsApplication.class);
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalGsApplication.class, args);
    }
}
