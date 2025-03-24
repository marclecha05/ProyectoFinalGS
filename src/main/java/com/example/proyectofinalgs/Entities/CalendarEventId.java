package com.example.proyectofinalgs.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CalendarEventId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Aquí puedes agregar más atributos si son necesarios


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}