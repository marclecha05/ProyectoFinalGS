package com.example.proyectofinalgs.Entities;

import com.example.proyectofinalgs.Entities.Proveedor;
import jakarta.persistence.*;

@Entity
@Table(name = "Horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    private String diaSemana; // Ejemplo: Lunes, Martes, etc.
    private String aperturaManana;
    private String cierreManana;
    private String aperturaTarde;
    private String cierreTarde;
    private boolean cerrado; // Indica si el día está cerrado

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getAperturaManana() {
        return aperturaManana;
    }

    public void setAperturaManana(String aperturaManana) {
        this.aperturaManana = aperturaManana;
    }

    public String getCierreManana() {
        return cierreManana;
    }

    public void setCierreManana(String cierreManana) {
        this.cierreManana = cierreManana;
    }

    public String getAperturaTarde() {
        return aperturaTarde;
    }

    public void setAperturaTarde(String aperturaTarde) {
        this.aperturaTarde = aperturaTarde;
    }

    public String getCierreTarde() {
        return cierreTarde;
    }

    public void setCierreTarde(String cierreTarde) {
        this.cierreTarde = cierreTarde;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }
}
