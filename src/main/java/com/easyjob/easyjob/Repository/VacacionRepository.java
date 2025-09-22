package com.easyjob.easyjob.Repository;

import com.easyjob.easyjob.Model.Vacacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VacacionRepository extends JpaRepository<Vacacion, Integer> {
    List<Vacacion> findByIdusuarios(Integer idusuarios);
}
