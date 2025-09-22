package com.easyjob.easyjob.Service;

import com.easyjob.easyjob.Model.Incapacidad;
import com.easyjob.easyjob.Repository.IncapacidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncapacidadService {

    @Autowired
    private IncapacidadRepository incapacidadRepository;

    public Incapacidad guardar(Incapacidad incapacidad) {
        return incapacidadRepository.save(incapacidad);
    }

    public List<Incapacidad> obtenerPorUsuario(Integer idusuarios) {
        return incapacidadRepository.findByIdusuarios(idusuarios);
    }

    public List<Incapacidad> obtenerTodas() {
        return incapacidadRepository.findAll();
    }
}
