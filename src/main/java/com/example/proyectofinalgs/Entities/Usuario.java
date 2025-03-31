package com.example.proyectofinalgs.Entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;
    @NonNull
    @Column(nullable = false)
    private String password;

    @Column()
    private String rol;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Proveedor proveedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
