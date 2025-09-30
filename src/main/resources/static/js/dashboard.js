document.addEventListener("DOMContentLoaded", function () {
  const toggleSidebar = document.getElementById("toggleSidebar");
  const sidebar = document.getElementById("sidebar");

  const tarjetaBienvenida = document.getElementById("tarjetaBienvenida");
  const hojaDeVida = document.getElementById("hojaDeVida");
  const seccionIncapacidades = document.getElementById("seccionIncapacidades");
  const seccionHorario = document.getElementById("seccionHorario");
  const seccionVacacionesForm = document.getElementById("seccionVacacionesForm");
  const seccionVacacionesHistorial = document.getElementById("seccionVacacionesHistorial");
  const incapacidadForm = document.getElementById("incapacidadForm");
  const mensajeExito = document.getElementById("mensaje-exito");
  const mensajeExitoIncapacidad = document.getElementById("mensaje-exito-incapacidad");
  const mensajeErrorIncapacidad = document.getElementById("mensaje-error-incapacidad");

  // Toggle menú lateral (mobile)
  if (toggleSidebar) {
    toggleSidebar.addEventListener("click", function () {
      sidebar.classList.toggle("active");
    });
  }

  // Toggle dropdowns
  document.querySelectorAll(".dropdown-btn").forEach(btn => {
    btn.addEventListener("click", function () {
      const next = this.nextElementSibling;
      if (!next) return;
      next.classList.toggle("show");
    });
  });

  // Ocultar todas las secciones
  function ocultarTodo() {
    [tarjetaBienvenida, hojaDeVida, seccionIncapacidades, seccionHorario, seccionVacacionesForm, seccionVacacionesHistorial].forEach(s => {
      if (s) s.style.display = "none";
    });
  }

  // Cerrar sidebar en móvil
  function cerrarSidebarSiMobile() {
    if (window.innerWidth < 768 && sidebar.classList.contains("active")) {
      sidebar.classList.remove("active");
    }
  }

  // Mapear botones a secciones
  const map = {
    btnHojaDeVida: () => { ocultarTodo(); if (hojaDeVida) hojaDeVida.style.display = "block"; cerrarSidebarSiMobile(); },
    btnVacacionesSolicitar: () => { ocultarTodo(); if (seccionVacacionesForm) seccionVacacionesForm.style.display = "block"; cerrarSidebarSiMobile(); },
    btnVacacionesHistorial: () => { ocultarTodo(); if (seccionVacacionesHistorial) seccionVacacionesHistorial.style.display = "block"; cerrarSidebarSiMobile(); },
    btnIncapacidades: () => { ocultarTodo(); if (seccionIncapacidades) seccionIncapacidades.style.display = "block"; cerrarSidebarSiMobile(); },
    btnHorario: () => { ocultarTodo(); if (seccionHorario) seccionHorario.style.display = "block"; cerrarSidebarSiMobile(); }
  };

  Object.keys(map).forEach(id => {
    const el = document.getElementById(id);
    if (el) {
      el.addEventListener("click", function (e) {
        e.preventDefault();
        map[id]();
      });
    }
  });

  // ======================
  // INCAPACIDADES - Envío al backend vía fetch
  // ======================
  if (incapacidadForm) {
    incapacidadForm.addEventListener("submit", async function (e) {
      e.preventDefault(); // prevenir recarga
      const formData = new FormData(incapacidadForm);

      try {
        const response = await fetch('/incapacidades/guardar', {
          method: 'POST',
          body: formData
        });

        if (response.ok) {
          mensajeExito.textContent = "✅ Incapacidad registrada y soporte subido correctamente";
          mensajeExito.className = "alert alert-success";
          mensajeExito.style.display = "block";
          incapacidadForm.reset();
        } else {
          mensajeExito.textContent = "❌ Error al registrar la incapacidad";
          mensajeExito.className = "alert alert-danger";
          mensajeExito.style.display = "block";
        }
      } catch (error) {
        console.error(error);
        mensajeExito.textContent = "❌ Error al enviar la incapacidad";
        mensajeExito.className = "alert alert-danger";
        mensajeExito.style.display = "block";
      }

      setTimeout(() => { mensajeExito.style.display = "none"; }, 6000);
    });
  }

  // Mostrar bienvenida al iniciar
  ocultarTodo();
  if (tarjetaBienvenida) tarjetaBienvenida.style.display = "block";

  // ======================
  // Mostrar sección de incapacidades si hay mensaje Thymeleaf
  // ======================
  if ((mensajeExitoIncapacidad && mensajeExitoIncapacidad.textContent.trim() !== "") ||
      (mensajeErrorIncapacidad && mensajeErrorIncapacidad.textContent.trim() !== "")) {
    ocultarTodo();
    if (seccionIncapacidades) seccionIncapacidades.style.display = "block";
  }

  // Evento del logo → muestra bienvenida
  const btnLogo = document.getElementById("btnLogo");
  if (btnLogo) {
    btnLogo.addEventListener("click", (e) => {
      e.preventDefault();
      ocultarTodo();
      if (tarjetaBienvenida) tarjetaBienvenida.style.display = "block";
    });
  }
});
