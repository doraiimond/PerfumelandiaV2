document.addEventListener("DOMContentLoaded", () => {
  fetch("http://192.168.1.24:8080/api/v2/productos")
    .then(res => res.json())
    .then(data => {
      const productos = data._embedded?.productoList || [];
      productos.forEach(producto => {
        const stockSpan = document.querySelector(`.stock[data-id='${producto.id}']`);
        if (stockSpan) {
          stockSpan.textContent = producto.stock;
        }
      });
    })
    .catch(err => {
      console.error("Error cargando el stock:", err);
    });
});
