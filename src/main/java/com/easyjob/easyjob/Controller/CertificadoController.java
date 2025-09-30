package com.easyjob.easyjob.Controller;

import com.easyjob.easyjob.Model.Usuario;
import com.easyjob.easyjob.Service.CertificadoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/certificado")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @GetMapping
    public ResponseEntity<InputStreamResource> descargarCertificado(HttpSession session) {
        // Recuperar usuario logueado de la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario"); // coincide con LoginController

        if (usuario == null) {
            return ResponseEntity.status(401).build(); // No autorizado si no hay sesión
        }

        // Generar el PDF del certificado
        ByteArrayInputStream bis = certificadoService.generarCertificado(usuario);

        // Configurar headers de respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=certificado_" + usuario.getIdusuarios() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
