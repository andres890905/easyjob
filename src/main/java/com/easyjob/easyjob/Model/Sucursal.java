package com.easyjob.easyjob.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sucursal;

    private String nombreSucursal;
    private String direccion;
    private String ciudad;
    private String correo;
    private String telefono;

    // 🔹 Constructor vacío (requerido por JPA)
    public Sucursal() {
    }

    // 🔹 Constructor con parámetros
    public Sucursal(Integer id_sucursal, String nombreSucursal, String direccion, String ciudad, String correo, String telefono) {
        this.id_sucursal = id_sucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.telefono = telefono;
    }

    // 🔹 Getters y Setters
    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

