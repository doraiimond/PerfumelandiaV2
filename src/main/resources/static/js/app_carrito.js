// Este archivo contiene el código JavaScript para la gestión de libros en la aplicación web.
// Se utiliza para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
const API_URL = "http://localhost:8080/api/v1/Carrito"; // URL de la API para acceder a los libros
// Función para listar los libros en la tabla
// Se utiliza la API Fetch para obtener los datos de los libros desde el servidor
function listarPerfume() {
    fetch(API_URL)
        .then(response => response.json())
        .then(perfumes => {
            const tbody = document.querySelector("#tablaProducto tbody");
            tbody.innerHTML = "";
            perfumes.forEach(producto => {
                const fila = `
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.stock}</td>
                        <td>${producto.precio}</td>
                        <td> 
                            <button class="btn btn-danger btn-sm" onclick="eliminarPerfume(${producto.id})">🗑️ Eliminar</button> // Botón para eliminar el perfume con class de Bootstrap
                            <button class="btn btn-warning btn-sm" onclick="buscarPerfume${producto.id})">✏️ Editar</button> // Botón para editar el perfume con class de Bootstrap
                        </td>
                    </tr>
                `;
                tbody.innerHTML += fila;
            });
        });
}
let perfumes = []; // Variable para almacenar la lista de perfumes
// Función para agregar un libro
function agregarPerfumes() {
    const nombre = document.getElementById("nombre").value;
    const stock = document.getElementById("stock").value;
    const precio = document.getElementById("precio").value;
    
    const nuevoLibro = {
        nombre,
        stock,
        precio
    };
    // Enviar el nuevo libro al servidor
    // Se utiliza la API Fetch para enviar los datos al servidor
    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoPerfume)
    })// Enviar el nuevo libro al servidor
    .then(response => response.json())
    .then(data => {
        alert("Perfume agregado exitosamente");
        listarPerfume();// Actualizar la tabla de libros
        limpiarFormulario();// Limpiar el formulario
    });
}
// Función para eliminar un libro
function eliminarPerfume(id) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Perfume eliminado exitosamente");
                listarPerfume();
            }
        });
}

// Función para actualizar un libro
// Se utiliza la API Fetch para enviar los datos actualizados al servidor
function actualizarPerfume(id) {
    const nombre = document.getElementById("nombre").value;
    const stock = document.getElementById("stock").value;
    const precio = document.getElementById("precio").value;
   

    const libroActualizado = {
        id: id,
        nombre: nombre,
        stock: stock,
        precio: precio,
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(perfumeActualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert("Perfume actualizado exitosamente");
        listarPerfume();
        limpiarFormulario();
    });
}
// Función para limpiar el formulario después de agregar o actualizar un libro
// Se utiliza para restaurar el formulario a su estado inicial
function limpiarFormulario() {
    document.getElementById("nombre").value = "";
    document.getElementById("stock").value = "";
    document.getElementById("precio").value = "";

    // Restaurar botón
    const boton = document.getElementById("botonFormulario");
    boton.innerText = "Agregar Perfume";
    boton.setAttribute("onclick", "agregarLibro()");

    // Resetear la variable global
    perfumeEnEdicionId = null; // Resetear el ID después de limpiar
}

// Cargar libros al abrir la página

listarPerfume();