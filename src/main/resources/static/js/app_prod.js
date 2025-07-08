    
const API_URL = "http://192.168.1.10:8080/api/v2/productos";
    
    function agregarProducto() {
      const nombre = document.getElementById("nombre").value;
      const marca = document.getElementById("marca").value;
      const stock = parseInt(document.getElementById("stock").value);
      const precio = parseInt(document.getElementById("precio").value);

      fetch("http://192.168.1.10:8080/api/v2/productos", {
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