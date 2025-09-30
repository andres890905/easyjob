package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Rol;
import com.easyjob.easyjob.Model.Sucursal;
import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Repository.RolRepository;
import com.easyjob.easyjob.Repository.SucursalRepository;
import com.easyjob.easyjob.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    // 👉 Vista principal del dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Rol> roles = rolRepository.findAll();
        List<Sucursal> sucursales = sucursalRepository.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        model.addAttribute("sucursales", sucursales);

        return "dashboard_supervisor";
    }

    // 👉 Buscar empleado (JSON)
    @PostMapping("/buscar")
    @ResponseBody
    public ResponseEntity<?> buscarEmpleado(@RequestParam("idEmpleado") Integer idEmpleado) {
        Optional<Usuario> empleado = usuarioRepository.findById(idEmpleado);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(empleado.get());
        } else {
            return ResponseEntity.badRequest().body("Empleado no encontrado");
        }
    }

    // 👉 Guardar empleado (sin refrescar)
    @PostMapping("/guardar")
    @ResponseBody
    public ResponseEntity<?> guardarEmpleado(@ModelAttribute Usuario usuario) {
        try {
            // Mantener contraseña si no se envía
            if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                Usuario existente = usuarioRepository.findById(usuario.getIdusuarios()).orElse(null);
                if (existente != null) {
                    usuario.setContrasena(existente.getContrasena());
                }
            }

            // Rol por defecto si no se asigna
            if (usuario.getRol() == null) {
                Rol rolPorDefecto = new Rol();
                rolPorDefecto.setId_roles(7001);
                usuario.setRol(rolPorDefecto);
            }

            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Empleado guardado correctamente ✅");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar el empleado ❌");
        }
    }

    // 👉 Eliminar empleado (sin refrescar)
    @PostMapping("/eliminar")
    @ResponseBody
    public ResponseEntity<?> eliminarEmpleado(@RequestParam("idEmpleado") Integer idEmpleado) {
        try {
            usuarioRepository.deleteById(idEmpleado);
            return ResponseEntity.ok("Empleado eliminado correctamente ✅");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el empleado ❌");
        }
    }

    // 👉 Endpoint para obtener sucursales en JSON
    @GetMapping("/sucursales")
    @ResponseBody
    public List<Sucursal> listarSucursales() {
        return sucursalRepository.findAll();
    }
}
