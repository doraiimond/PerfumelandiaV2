    const API_URL = "http://localhost:8080/api/v1/usuarios";
    function registrar() {
      const nombre = document.getElementById("nombre").value.trim();
      const email = document.getElementById("email").value.trim();
      const password = document.getElementById("password").value.trim();

      if (!nombre || !email || !password) {
        alert("Por favor completa todos los campos.");
        return;
      }

      fetch("http://localhost:8080/api/v1/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre, email, password })
      })
      .then(response => {
        if (response.ok) {
          alert("Usuario registrado correctamente!");
          window.location.href = "login.html";
        } else {
          alert("Error al registrar. Verifiaca los datos.");
        }
      });
    }