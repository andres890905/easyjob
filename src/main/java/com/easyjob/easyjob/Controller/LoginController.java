package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // la vista login.html
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("correo") String correo,
                                @RequestParam("contrasena") String contrasena,
                                HttpSession session,
                                Model model) {

        // Buscar usuario por correo
        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            // Guardamos el usuario en sesión
            session.setAttribute("usuario", usuario);

            // ✅ Redirigir según rol
            Integer idRol = usuario.getRol().getId_roles();

            if (idRol == 7001 || idRol == 7002 || idRol == 7004) {
                return "redirect:/dashboard_empleado";
            } else if (idRol == 7003) {
                return "redirect:/dashboard_supervisor";
            } else {
                model.addAttribute("error", "Rol no válido");
                return "login";
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login";
        }
    }

   @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 🔥 Destruye la sesión
        return "redirect:/login?logout"; // redirige al login con parámetro
    }
}
