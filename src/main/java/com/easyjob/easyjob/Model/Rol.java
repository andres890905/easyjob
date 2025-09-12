package com.easyjob.easyjob.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_roles;

    private String tipoRol;

    // 🔹 Constructor vacío (necesario para JPA)
    public Rol() {
    }

    // 🔹 Constructor con parámetros
    public Rol(Integer id_roles, String tipoRol) {
        this.id_roles = id_roles;
        this.tipoRol = tipoRol;
    }

    // 🔹 Getters y Setters
    public Integer getId_roles() {
        return id_roles;
    }

    public void setId_roles(Integer id_roles) {
        this.id_roles = id_roles;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
}
