const API_URL="http://192.168.1.10:8080/api/v2/reportes";

function enviarR(){
    const nombre = document.getElementById("nombre").value.trim();
    const email = document.getElementById("email").value.trim();
    const asunto = document.getElementById("asunto").value.trim();
      if (!nombre || !email || !asunto) {
        alert("completa todos los campos.");
        return;
      }

      fetch("http://192.168.1.10:8080/api/v2/reportes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre, email, asunto })
      })
      .then(response => {
        if (response.ok) {
          alert("Reporte Enviado correctamente!");
          window.location.href = "index.html";
        } else {
          alert("Error Verifiaca los campos.");
        }
      });
    }