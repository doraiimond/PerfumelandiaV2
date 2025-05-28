
document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8080/api/v1/productos")
    .then(res => res.json())
    .then(data => {
      data.forEach(producto => {
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