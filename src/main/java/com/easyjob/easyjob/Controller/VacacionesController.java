package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Model.Vacacion;
import com.easyjob.easyjob.Service.VacacionService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Controller
public class VacacionesController {

    @Autowired
    private VacacionService vacacionService;

    @PostMapping("/vacaciones/guardar")
    public String guardarVacacion(Vacacion vacacion, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        vacacion.setIdusuarios(usuario.getIdusuarios());
        vacacion.setEstado("Pendiente");
        vacacion.setFechaSolicitud(LocalDate.now());

        vacacionService.guardarSolicitud(vacacion);

        return "redirect:/dashboard_empleado?successVacaciones=true";
    }

    @GetMapping("/vacaciones/mis-solicitudes")
    public String misVacaciones(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Vacacion> vacaciones = vacacionService.obtenerPorUsuario(usuario.getIdusuarios());
        model.addAttribute("vacaciones", vacaciones);

        return "dashboard_empleado" ; // 🔹 Vista para listar solicitudes
    }
}
