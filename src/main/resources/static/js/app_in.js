document.addEventListener("DOMContentLoaded", () => {
  const usuario = JSON.parse(localStorage.getItem("usuario"));

  const btnLogin = document.getElementById("btn-login");
  const btnRegister = document.getElementById("btn-register");
  const btnCarrito = document.getElementById("btn-carrito");
  const btnProblema = document.getElementById("btn-problema");
  const btnLogout = document.getElementById("btn-logout");

  if (usuario) {
    btnLogin.classList.add("d-none");
    btnRegister.classList.add("d-none");
    btnCarrito.classList.remove("d-none");
    btnProblema.classList.remove("d-none");
    btnLogout.classList.remove("d-none");
  } else {
    btnLogin.classList.remove("d-none");
    btnRegister.classList.remove("d-none");
    btnCarrito.classList.add("d-none");
    btnProblema.classList.add("d-none");
    btnLogout.classList.add("d-none");
  }

  btnLogout.addEventListener("click", () => {
    localStorage.removeItem("usuario");
    window.location.reload();
  });
});
