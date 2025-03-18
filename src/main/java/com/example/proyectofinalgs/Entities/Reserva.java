package com.example.proyectofinalgs.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reserva {
    @Id
    private int id;
    private int cliente_id;
    private int provider_id;
    private String calendar_event_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getCalendar_event_id() {
        return calendar_event_id;
    }

    public void setCalendar_event_id(String calendar_event_id) {
        this.calendar_event_id = calendar_event_id;
    }
}
