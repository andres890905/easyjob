package com.easyjob.easyjob.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard_empleado")
    public String dashboardEmpleado(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login"; // sin sesión, vuelve al login
        }
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
