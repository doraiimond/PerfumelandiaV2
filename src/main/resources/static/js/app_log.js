const API_URL = "http://192.168.1.9:8080/api/v2/usuarios"; 
  
function login() {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
    
  if (!email || !password) {
    alert("Completa todos los campos");
    return;
  }

  fetch("http://192.168.1.9:8080/api/v2/usuarios/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  })
  .then(res => res.json())
  .then(data => {
    if (data && data.nombre) {
      alert("Inicio de sesión exitoso como " + data.nombre);
      localStorage.setItem("usuario", JSON.stringify(data));
      window.location.href = "index.html";
    } else {
      alert("Cuenta");
    }
  })
  .catch(err => {
    console.error(err);
    alert("Error al conectar con el servidor.");
  });

}

document.getElementById("btn-logout").addEventListener("click", () => {
  localStorage.removeItem("usuario");
  window.location.reload();
});


