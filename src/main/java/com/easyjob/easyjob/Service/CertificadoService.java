package com.easyjob.easyjob.Service;

import com.easyjob.easyjob.Model.Usuario;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CertificadoService {

    public ByteArrayInputStream generarCertificado(Usuario usuario) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Crear PdfWriter sin guardar en variable
            PdfWriter.getInstance(document, out);

            document.open();

            // --- Fuente y estilos ---
            Font tituloFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font subtituloFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font contenidoFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

            // --- Marco decorativo ---
            Rectangle rect = new Rectangle(36, 36, 559, 806); // margenes
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(2);
            document.add(rect);

            // --- Logo de la empresa (opcional) ---
            try {
                Image logo = Image.getInstance("src/main/resources/static/images/logo.png"); // ruta de tu logo
                logo.scaleToFit(100, 100);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (Exception e) {
                // si no hay logo, ignorar
            }

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // --- Título ---
            Paragraph titulo = new Paragraph("CERTIFICADO LABORAL", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // --- Encabezado ---
            Paragraph encabezado = new Paragraph(
                    "Se certifica que el(la) señor(a):", contenidoFont);
            document.add(encabezado);

            // Nombre completo en negrita y centrado
            Paragraph nombre = new Paragraph(
                    usuario.getNombre() + " " + usuario.getApellido(), subtituloFont);
            nombre.setAlignment(Element.ALIGN_CENTER);
            document.add(nombre);

            document.add(Chunk.NEWLINE);

            // --- Datos del empleado ---
            Paragraph datos = new Paragraph();
            datos.setFont(contenidoFont);
            datos.add("Cédula/ID: " + usuario.getIdusuarios() + "\n");
            datos.add("Correo: " + usuario.getCorreo() + "\n");
            datos.add("Teléfono: " + usuario.getTelefono() + "\n");
            datos.add("Dirección: " + (usuario.getDireccion() != null ? usuario.getDireccion() : "No registrada") + "\n");
            datos.add("Fecha de nacimiento: " + (usuario.getFechaNacimiento() != null ?
                    usuario.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "No registrada") + "\n");
            datos.add("Rol: " + usuario.getRol().getTipoRol() + "\n");
            datos.add("Sucursal: " + (usuario.getSucursal() != null ? usuario.getSucursal().getNombreSucursal() : "No registrada") + "\n");

            // Salario mínimo si no hay valor
            double salarioMinimo = 1160000.0;
            double salarioEmpleado = (usuario.getSalario() != null && usuario.getSalario() > 0) ? usuario.getSalario() : salarioMinimo;
            datos.add("Salario: $" + String.format("%,.2f", salarioEmpleado) + "\n");

            document.add(datos);

            document.add(Chunk.NEWLINE);

            // --- Pie ---
            Paragraph pie = new Paragraph(
                    "Este certificado se expide a solicitud del interesado para fines laborales.\n" +
                            "Fecha de expedición: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    contenidoFont);
            pie.setAlignment(Element.ALIGN_LEFT);
            document.add(pie);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // Firma simulada
            Paragraph firma = new Paragraph("_____________________________\nEmpresa EasyJob", contenidoFont);
            firma.setAlignment(Element.ALIGN_LEFT);
            document.add(firma);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
