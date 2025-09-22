package com.easyjob.easyjob.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacaciones")
public class Vacacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacacion")
    private Integer idVacacion; // PK de la tabla vacaciones

    @Column(name = "idusuarios", nullable = false)
    private Integer idusuarios; // Relación con la tabla usuarios

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false, length = 45)
    private String estado; // Pendiente, Aprobada, Rechazada

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    // ====== Getters y Setters ======
     public Integer getIdVacacion() { return idVacacion; }
    public void setIdVacacion(Integer idVacacion) { this.idVacacion = idVacacion; }

    public Integer getIdusuarios() { return idusuarios; }
    public void setIdusuarios(Integer idusuarios) { this.idusuarios = idusuarios; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDate fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}

