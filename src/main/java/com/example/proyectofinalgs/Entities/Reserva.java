package com.example.proyectofinalgs.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Proveedor proveedor;

    private String calendarEventId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalendarEventId() {
        return calendarEventId;
    }

    public void setCalendarEventId(String calendarEventId) {
        this.calendarEventId = calendarEventId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proveedor getProvider() {
        return proveedor;
    }

    public void setProvider(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCliente_id() {
        return cliente.getId();
    }
}
