package com.easyjob.easyjob.Repository;

import com.easyjob.easyjob.Model.Incapacidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncapacidadRepository extends JpaRepository<Incapacidad, String> {
    List<Incapacidad> findByIdusuarios(Integer idusuarios);
}
