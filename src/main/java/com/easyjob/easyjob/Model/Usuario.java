package com.easyjob.easyjob.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private Integer idusuarios; // Ahora el usuario escribe la cédula manualmente

    @ManyToOne
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

    @Column(name = "telefono", insertable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_roles", nullable = false)
    private Rol rol;

    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    
    private LocalDate fechaRegistro;
    private String direccion;
    private LocalDate fechaNacimiento;


    // ===== Getters y Setters =====
    public Integer getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(Integer idusuarios) {
        this.idusuarios = idusuarios;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombre() {   // 👈 getter correcto
        return nombre;
    }

    public void setNombre(String nombre) {  // 👈 setter correcto
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

     public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {   // 👈 getter correcto
    return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {  // 👈 setter correcto
        this.fechaRegistro = fechaRegistro;
}

    public String getDireccion() {
    return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

