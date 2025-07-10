    const API_URL = "http://192.168.1.24:8080/api/v2/usuarios";
    function registrar() {
      const nombre = document.getElementById("nombre").value.trim();
      const email = document.getElementById("email").value.trim();
      const password = document.getElementById("password").value.trim();

      if (!nombre || !email || !password) {
        alert("por favor completa todos los campos.");
        return;
      }

      fetch("http://192.168.1.24:8080/api/v2/usuarios/registrar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre , email , password })
      })
      .then(response => {
        if (response.ok) {
          alert("Usuario registrado correctamente");
          window.location.href = "login.html";
        } else {
          alert("Error verifiaca los datos.");
        }
      });
    }