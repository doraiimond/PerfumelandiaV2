API_URL = "http://localhost:8080/api/v1/carrito"; //se conecta con el usuario 

        function agregarAlCarrito(id) {
          fetch(`http://localhost:8080/api/v1/carrito/agregar/${id}`, {
            method: "POST"
          })
          .then(res => {
            if (res.ok) {
              alert("Producto agregado al carrito");
            } else {
              alert("No se pudo agregar el producto");
            }
         })
          .catch(error => {
            console.error("Error en la solicitud:", error);
            alert("Error de conexión");
         });
        }

    function eliminarDelCarrito(id) {
      fetch(`http://localhost:8080/api/v1/carrito/eliminar/${id}`, {
        method: "DELETE"
      }).then(() => {
        alert("Producto eliminado del carrito");
        cargarCarrito();
      });
    }

    const carrito = {
      vaciarCarrito: function () {
        fetch("http://localhost:8080/api/v1/carrito/vaciar", {
          method: "DELETE"
        }).then(() => {
          alert("Carrito vaciado");
          cargarCarrito();
        });
      },

      confirmarCompra: function () {
        // Funcionalidad adicional se puede implementar aquí
        alert("¡Compra confirmada! (funcionalidad futura)");
        this.vaciarCarrito();
      }
    };

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