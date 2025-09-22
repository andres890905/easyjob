package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Incapacidad;
import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Service.IncapacidadService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/incapacidades")
public class IncapacidadController {

    @Autowired
    private IncapacidadService incapacidadService;

    @PostMapping("/guardar")
    public String guardarIncapacidad(@RequestParam("fechaInicioIncapacidad") String fechaInicio,
                                     @RequestParam("fechaFinIncapacidad") String fechaFin,
                                     @RequestParam("motivoIncapacidad") String motivo,
                                     @RequestParam("archivoSoporte") MultipartFile archivo,
                                     HttpSession session) throws IOException {

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        // Generar un ID único
        String idIncapacidad = UUID.randomUUID().toString().substring(0, 11);

        // Guardar archivo soporte en carpeta local
        String carpeta = "C:/easyjob/uploads/incapacidades/";
        File dir = new File(carpeta);
        if (!dir.exists()) dir.mkdirs();

        String nombreArchivo = idIncapacidad + "_" + archivo.getOriginalFilename();
        File destino = new File(carpeta + nombreArchivo);
        archivo.transferTo(destino);

        // Crear objeto Incapacidad
        Incapacidad incapacidad = new Incapacidad();
        incapacidad.setIdIncapacidad(idIncapacidad);
        incapacidad.setIdusuarios(usuario.getIdusuarios());
        incapacidad.setNombreEmpleado(usuario.getNombre() + " " + usuario.getApellido());
        incapacidad.setNombreEps("EPS Pendiente"); // puedes pedirlo en el form si quieres
        incapacidad.setMotivo(motivo);
        incapacidad.setFechaInicio(LocalDate.parse(fechaInicio));
        incapacidad.setFechaFin(LocalDate.parse(fechaFin));
        incapacidad.setArchivoSoporte(destino.getAbsolutePath());

        incapacidadService.guardar(incapacidad);

        return "redirect:/dashboard_empleado?successIncapacidad=true";
    }

    @GetMapping("/mis-incapacidades")
    public String listarMisIncapacidades(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("incapacidades", incapacidadService.obtenerPorUsuario(usuario.getIdusuarios()));
        return "dashboard_empleado";
    }
}
