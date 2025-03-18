package com.example.proyectofinalgs.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Authorities {
    @Id
    private String username;
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
