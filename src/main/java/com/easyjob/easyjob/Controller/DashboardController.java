package com.easyjob.easyjob.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.easyjob.easyjob.Model.Usuario;

@Controller
public class DashboardController {

    @GetMapping("/dashboard_empleado")
    public String dashboardEmpleado(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; 
        }

        model.addAttribute("usuario", usuario); // ✅ enviamos datos a la vista
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
}
