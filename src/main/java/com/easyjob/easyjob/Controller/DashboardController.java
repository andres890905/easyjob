package com.easyjob.easyjob.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.easyjob.easyjob.Model.Incapacidad;
import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Model.Vacacion;
import com.easyjob.easyjob.Repository.UsuarioRepository;
import com.easyjob.easyjob.Service.VacacionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class DashboardController {

    @Autowired
    private VacacionService vacacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping("/dashboard_empleado")
    public String dashboardEmpleado(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; 
        }

        model.addAttribute("usuario", usuario); // ✅ enviamos datos a la vista

        model.addAttribute("vacacion", new Vacacion());
        model.addAttribute("vacaciones", vacacionService.obtenerPorUsuario(usuario.getIdusuarios()));
        model.addAttribute("incapacidad", new Incapacidad());  

        return "dashboard_empleado";
    }
    @GetMapping("/dashboard_supervisor")
    public String dashboardSupervisor(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "dashboard_supervisor";
    }

    @GetMapping("/dashboard_admin")
    public String dashboardAdmin(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "dashboard_admin";
    }
    @PostMapping("/guardarHojaDeVida")
    public String guardarHojaDeVida(@ModelAttribute Usuario usuario, HttpSession session) {
    Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

    if (usuarioSesion == null) {
        return "redirect:/login";
    }

    // Actualizamos los datos del usuario en sesión
    usuarioSesion.setNombre(usuario.getNombre());
    usuarioSesion.setApellido(usuario.getApellido());
    usuarioSesion.setCorreo(usuario.getCorreo());
    usuarioSesion.setTelefono(usuario.getTelefono());
    usuarioSesion.setDireccion(usuario.getDireccion());
    usuarioSesion.setFechaNacimiento(usuario.getFechaNacimiento());

    usuarioRepository.save(usuarioSesion); // 🔹 Se guarda en la BD

    // Refrescamos la sesión
    session.setAttribute("usuario", usuarioSesion);

    return "redirect:/dashboard_empleado?success=true";
}

}
