const API_URL = "http://localhost:8080/api/v1/carrito";


function agregarAlCarrito (id) {
    fetch(`http://localhost:8080/api/v1/carrito/agregar/${id}`, {
      method: "POST"
    })
    .then(res => {
      if (res.ok) {
        alert("Producto agregado al carrito");
      } else {
        alert("No se pudo agregar el producto");
      }
   });

}

function eliminarDelCarrito(id) {
  fetch("http://localhost:8080/api/v1/carrito/eliminar/${id}", { method: "DELETE" })
    .then(() => {
      alert("Producto eliminado del carrito");
      cargarCarrito();
    });
}

function vaciarCarrito() {
  fetch("http://localhost:8080/api/v1/carrito/vaciar", { method: "DELETE" })
    .then(() => {
      alert("Carrito vaciado");
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
    alert("Compra Realizada");
    cargarCarrito(); 
  })
  .catch(err => {
    alert("ubo un problema al procesar la compra");
  });
}


function cargarCarrito() {
  fetch("http://localhost:8080/api/v1/carrito")
    .then(res => res.json())
    .then(data => {
      const tbody = document.querySelector("#tablaCarrito tbody");
      const total = document.getElementById("totalCarrito");
      tbody.innerHTML = "";
      
      data.forEach(p => {
        tbody.innerHTML += `
          <tr>
            <td>${p.id}</td>
            <td>${p.nombre}</td>
            <td>${p.marca}</td>
            <td>${p.precio}</td>
            <td>${p.stock}</td>
            <td><button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(${p.id})">🗑️</button></td>
          </tr>
        `;
      });

      total.textContent = data.length;
    });
}

window.onload = cargarCarrito();
