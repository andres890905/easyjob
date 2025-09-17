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

  // Toggle menú lateral (mobile)
  if (toggleSidebar) {
    toggleSidebar.addEventListener("click", function () {
      sidebar.classList.toggle("active");
    });
  }

  // Toggle visual de dropdowns
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

  // Helpers para cerrar sidebar en móvil
  function cerrarSidebarSiMobile() {
    if (window.innerWidth < 768 && sidebar.classList.contains("active")) {
      sidebar.classList.remove("active");
    }
  }

  // Mapeo de botones -> Secciones
  const map = {
    btnHojaDeVida: () => { ocultarTodo(); if (hojaDeVida) hojaDeVida.style.display = "block"; cerrarSidebarSiMobile(); },
    btnVacacionesSolicitar: () => { ocultarTodo(); if (seccionVacacionesForm) seccionVacacionesForm.style.display = "block"; cerrarSidebarSiMobile(); },
    btnVacacionesHistorial: () => { ocultarTodo(); if (seccionVacacionesHistorial) seccionVacacionesHistorial.style.display = "block"; renderVacacionesHistorial(); cerrarSidebarSiMobile(); },
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
  // VACACIONES
  // ======================
  let vacacionesData = [
    { fecha_inicio: '2025-06-01', fecha_fin: '2025-06-15', motivo: 'Viaje familiar', estado: 'Aprobado' },
    { fecha_inicio: '2024-12-10', fecha_fin: '2024-12-20', motivo: 'Vacaciones de fin de año', estado: 'Rechazado' },
    { fecha_inicio: '2024-05-05', fecha_fin: '2024-05-12', motivo: 'Descanso personal', estado: 'Pendiente' }
  ];

  function renderVacacionesHistorial() {
    const tbody = document.querySelector("#seccionVacacionesHistorial tbody");
    if (!tbody) return;
    tbody.innerHTML = "";
    vacacionesData.forEach(item => {
      const tr = document.createElement("tr");
      tr.innerHTML = `<td>${item.fecha_inicio}</td>
                      <td>${item.fecha_fin}</td>
                      <td>${escapeHtml(item.motivo)}</td>
                      <td>${badgeForEstado(item.estado)}</td>`;
      tbody.appendChild(tr);
    });
  }

  function badgeForEstado(estado) {
    const e = (estado || '').toLowerCase();
    if (e === 'aprobado') return '<span class="badge bg-success">Aprobado</span>';
    if (e === 'rechazado') return '<span class="badge bg-danger">Rechazado</span>';
    return '<span class="badge bg-warning text-dark">Pendiente</span>';
  }

  function escapeHtml(text = '') {
    return text.replace(/[&<>"']/g, function (m) {
      return ({ '&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#039;' })[m];
    });
  }

  // Manejo formulario vacaciones
  const formVac = document.getElementById("formVacaciones");
  if (formVac) {
    formVac.addEventListener("submit", function (e) {
      e.preventDefault();
      const inicio = document.getElementById("fecha_inicio").value;
      const fin = document.getElementById("fecha_fin").value;
      const motivo = document.getElementById("motivo").value.trim();

      if (!inicio || !fin) {
        alert("Por favor completa las fechas.");
        return;
      }
      if (new Date(inicio) > new Date(fin)) {
        alert("La fecha de inicio no puede ser posterior a la fecha de fin.");
        return;
      }

      vacacionesData.push({ fecha_inicio: inicio, fecha_fin: fin, motivo: motivo, estado: 'Pendiente' });

      alert("Solicitud enviada ✅");

      formVac.reset();
      ocultarTodo();
      if (seccionVacacionesHistorial) seccionVacacionesHistorial.style.display = "block";
      renderVacacionesHistorial();
    });
  }

  // ======================
  // INCAPACIDADES
  // ======================
  if (incapacidadForm) {
    incapacidadForm.addEventListener("submit", function (e) {
      e.preventDefault();
      alert("Incapacidad registrada y soporte subido ✅ (se guardará en BD en el futuro)");
      incapacidadForm.reset();
    });
  }

  // Mostrar bienvenida al iniciar
  ocultarTodo();
  if (tarjetaBienvenida) tarjetaBienvenida.style.display = "block";
});
document.getElementById("btnLogo").addEventListener("click", (e) => {
  e.preventDefault();
  mostrarSeccion("tarjetaBienvenida");
});
// Función para ocultar todas las secciones
function ocultarTodo() {
  [tarjetaBienvenida, hojaDeVida, seccionIncapacidades, seccionHorario, seccionVacacionesForm, seccionVacacionesHistorial].forEach(s => {
    if (s) s.style.display = "none";
  });
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
