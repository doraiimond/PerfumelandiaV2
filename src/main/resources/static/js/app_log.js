const API_URL = "http://localhost:8080/api/v1/usuarios/login"; //se conecta con el usuario 
  
function login() {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
    
  if (!email || !password) {
    alert("Completa todos los campos");
    return;
  }

  fetch("http://localhost:8080/api/v1/usuarios/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  })
  .then(res => res.json())
  .then(data => {
    if (data && data.nombre) {
      alert("Inicio de sesión exitoso como " + data.nombre);

      window.location.href = "index.html";
    } else {
      alert("Cuenta no encontrada. Veriaafica tus credenciales.");
    }
  })
  .catch(err => {
    console.error(err);
    alert("Error al conectar con el servidor.");
  });
}