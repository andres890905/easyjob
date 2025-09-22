package com.easyjob.easyjob.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incapacidades")
public class Incapacidad {

    @Id
    @Column(name = "id_incapacidad", length = 11)
    private String idIncapacidad; // varchar(11) PK

    @Column(name = "idusuarios", nullable = false)
    private Integer idusuarios;

    @Column(name = "nombre_empleado", nullable = false, length = 255)
    private String nombreEmpleado;

    @Column(name = "nombre_eps", columnDefinition = "TEXT", nullable = false)
    private String nombreEps;

    @Column(nullable = false, length = 255)
    private String motivo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "archivo_soporte", nullable = false)
    private String archivoSoporte; // ruta donde se guarda el archivo

    // ===== Getters y Setters =====
    public String getIdIncapacidad() { return idIncapacidad; }
    public void setIdIncapacidad(String idIncapacidad) { this.idIncapacidad = idIncapacidad; }

    public Integer getIdusuarios() { return idusuarios; }
    public void setIdusuarios(Integer idusuarios) { this.idusuarios = idusuarios; }

    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }

    public String getNombreEps() { return nombreEps; }
    public void setNombreEps(String nombreEps) { this.nombreEps = nombreEps; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getArchivoSoporte() { return archivoSoporte; }
    public void setArchivoSoporte(String archivoSoporte) { this.archivoSoporte = archivoSoporte; }
}
