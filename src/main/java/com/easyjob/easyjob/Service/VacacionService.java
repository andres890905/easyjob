package com.easyjob.easyjob.Service;

import com.easyjob.easyjob.Model.Vacacion;
import com.easyjob.easyjob.Repository.VacacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VacacionService {

    @Autowired
    private VacacionRepository vacacionRepository;

    public Vacacion guardarSolicitud(Vacacion vacacion) {
        return vacacionRepository.save(vacacion);
    }

    public List<Vacacion> obtenerPorUsuario(Integer idusuarios) {
        return vacacionRepository.findByIdusuarios(idusuarios);
    }

    public List<Vacacion> obtenerTodas() {
        return vacacionRepository.findAll();
    }

    public Vacacion cambiarEstado(Integer idVacacion, String nuevoEstado) {
        Vacacion vacacion = vacacionRepository.findById(idVacacion).orElse(null);
        if (vacacion != null) {
            vacacion.setEstado(nuevoEstado);
            return vacacionRepository.save(vacacion);
        }
        return null;
    }
}
