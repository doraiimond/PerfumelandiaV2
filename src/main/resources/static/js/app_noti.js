function cargarNotificaciones() {
  fetch("http://192.168.1.24:8080/api/v2/notificaciones")
    .then(res => res.json())
    .then(data => {
      const notificaciones = data._embedded?.notificacionList || [];
      const tbody = document.querySelector("#tablaNotificaciones tbody");
      tbody.innerHTML = "";

      notificaciones.forEach(n => {
        const fila = `<tr><td>${n.asunto}</td><td>${n.descripcion}</td></tr>`;
        tbody.innerHTML += fila;
      });
    })
    .catch(error => console.error("Error al cargar notificaciones:", error));
}

window.onload = cargarNotificaciones;
