    
const API_URL = "http://localhost:8080/api/v1/productos";
    
    function agregarProducto() {
      const nombre = document.getElementById("nombre").value;
      const marca = document.getElementById("marca").value;
      const stock = parseInt(document.getElementById("stock").value);
      const precio = parseInt(document.getElementById("precio").value);

      fetch("http://localhost:8080/api/v1/productos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ nombre, marca, stock, precio })
      })
      .then(res => {
        if (res.ok) {
          alert("Producto agregado correctamente");
        } else {
          alert("Error al agregar producto");
        }
      });
    }