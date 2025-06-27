function cargarNotificaciones() {
  fetch("http://192.168.1.7:8080/api/v1/notificaciones")
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
