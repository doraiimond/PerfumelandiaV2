const API_URL = "http://localhost:8080/api/v1/carrito";


function agregarAlCarrito (id) {
  fetch(`${API_URL}/agregar/${id}`, { method: "POST" })
    .then(res => {
      if (res.ok) {
        alert("Producto agregado al carrito");
      } else {
        alert("No se pudo agregar el producto");
      }
   });
}

async function eliminarDelCarrito(id) {
  try{
    const response = await fetch (`${API_URL}/eliminar/${id}`, {method: "DELETE"});
    alert("Producto Eliminado");
      fetch("http://localhost:8080/api/v1/notificaciones/agregar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          asunto: "Movimiento",
          descripcion: `Producto eliminado: ${id}`
      })
    });               
    await cargarCarrito();
  }catch(error){
    console.error("Error al eliminar producto",error);
  }
}

function vaciarCarrito() {
  fetch(`${API_URL}/vaciar`, { method: "DELETE" })
    .then(() => {
      alert("Carrito vaciado");
      agregarNotificacion("Carrito Vaciado","Has vaciado tu carrito con productos dentro.");
      cargarCarrito();
    });
}

function confirmarCompra() {
  fetch("http://localhost:8080/api/v1/carrito/confirmar", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  })
  .then(res => res.text())
  .then(mensaje => {
    alert(mensaje);
    fetch(`${API_URL}`)
      .then(res => res.json())
        console.log("Compra echa")
          fetch("http://localhost:8080/api/v1/notificaciones/agregar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              asunto: "Movimiento",
              descripcion:"Se a arealizado una compra"
          })
        });
      cargarCarrito(); 
      });
}

function cargarCarrito() {
      fetch("http://localhost:8080/api/v1/carrito")
        .then(res => res.json())
        .then(data => {
          const tbody = document.querySelector("#tablaCarrito tbody");
          const total = document.getElementById("totalCarrito");
          tbody.innerHTML = "";
          let contador = 0;
          data.forEach(p => {
            contador++;
            const fila = `
              <tr>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.marca}</td>
                <td>${p.precio}</td>
                <td>
                  <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${p.id})">🗑️</button>
                </td>
              </tr>
            `;
            tbody.innerHTML += fila;
          });

          total.textContent = contador;
        });
}

cargarCarrito();
window.onload = cargarCarrito;