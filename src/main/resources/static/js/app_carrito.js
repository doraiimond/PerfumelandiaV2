const API_URL = "http://192.168.1.24:8080/api/v2/carrito";

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
    await agregarNotificacion("Movimiento", `producto eliminado: ${id}`);  
    await cargarCarrito();
  }catch(error){
    console.error("Error al eliminar producto",error);
  }
}

function agregarNotificacion(asunto, descripcion) {
  return fetch("http://192.168.1.24:8080/api/v2/notificaciones", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ asunto, descripcion }),
  })
  .then(res => {
    if (!res.ok) throw new Error("Error al agregar la notificación");
  });
}



function vaciarCarrito() {
  fetch(`${API_URL}/vaciar`, { method: "DELETE" })
    .then(() => {
      agregarNotificacion("Movimiento", "Carrito Vaciado");
      alert("Carrito vaciado");
      cargarCarrito();
    });
}

function confirmarCompra() {
  fetch("http://192.168.1.24:8080/api/v2/carrito/confirmar", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  })
  .then(res => res.text())
  .then(mensaje => {
    alert(mensaje);
    fetch(`${API_URL}`)
      .then(res => res.json())
        console.log("Compra echa")
          fetch("http://192.168.1.24:8080/api/v2/notificaciones/agregar", {
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
  fetch("http://192.168.1.24:8080/api/v2/carrito")
    .then(res => res.json())
    .then(data => {
      const productos = data._embedded?.productoList || [];
      const tbody = document.querySelector("#tablaCarrito tbody");
      const total = document.getElementById("totalCarrito");
      tbody.innerHTML = "";
      let contador = 0;

      productos.forEach(p => {
        contador++;
        const fila = `
          <tr>
            <td>${p.id}</td>
            <td>${p.nombre}</td>
            <td>${p.marca}</td>
            <td>${p.precio}</td>
            <td>${p.stock}</td>
            <td>
              <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${p.id})">🗑️</button>
            </td>
          </tr>
        `;
        tbody.innerHTML += fila;
      });

      total.textContent = contador;
    })
    .catch(error => console.error("Error al cargar carrito:", error));
}


window.onload = cargarCarrito;