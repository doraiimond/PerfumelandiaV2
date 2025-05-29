const API_URL="http://localhost:8080/api/v1/reportes";

function enviarR(){
    const nombre = document.getElementById("nombre").value.trim();
    const email = document.getElementById("email").value.trim();
    const asunto = document.getElementById("asunto").value.trim();
      if (!nombre || !email || !asunto) {
        alert("Por favor completa todos los campos.");
        return;
      }

      fetch("http://localhost:8080/api/v1/reportes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre, email, asunto })
      })
      .then(response => {
        if (response.ok) {
          alert("Reporte Enviado correctamente!");
          window.location.href = "index.html";
        } else {
          alert("Error al Enviar. Verifiaca los campos.");
        }
      });
    }