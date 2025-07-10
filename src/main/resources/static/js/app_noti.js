function cargarNotificaciones() {
  fetch("http://192.168.1.9:8080/api/v2/notificaciones")
    .then(res => res.json())
    .then(data => {
      const tbody = document.querySelector("#tablaNotificaciones tbody");
      tbody.innerHTML = "";

      data.forEach(n => {
        const fila = `<tr><td>${n.asunto}</td><td>${n.descripcion}</td></tr>`;
        tbody.innerHTML += fila;
      });
    });
}

window.onload = cargarNotificaciones;
