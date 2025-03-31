// Definimos un array vacío para almacenar los usuarios
/*let users = [];

// Llamamos a la función getUsers para cargar los usuarios
getUsers();

// Función para obtener los usuarios del servidor
function getUsers() {
    $.get("/users", (data) => {
        console.log(data);
    })
        .done((data) => {
            // Almacenamos los usuarios obtenidos en el array
            users = data;

            // Llamamos a la función renderUsers para mostrar los usuarios
            renderUsers();
        })
        .fail((error) => alert(error));
}

// Función para renderizar los usuarios en la lista
function renderUsers(filter = "") {
    const userList = document.getElementById("userList");
    userList.innerHTML = "";

    // Filtramos y mostramos los usuarios que coincidan con el filtro
    users
        .filter(user => user.username.toLowerCase().includes(filter.toLowerCase()))
        .forEach(user => {
            const div = document.createElement("div");
            div.className = "user-item p-1 border-bottom";
            div.textContent = user.username;
            div.style.cursor = "pointer";
            div.onclick = () => selectUser(user);
            userList.appendChild(div);
        });
}

// Función para seleccionar un usuario y mostrar sus detalles
function selectUser(user) {
    document.getElementById("selectedUser").innerHTML = `
        <p hidden id="userID">${user.id}</p>
        <p><strong>Nombre:</strong> ${user.username}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <button class="btn btn-danger" id="delete">Eliminar</button>
    `;
}

// Evento para filtrar los usuarios por nombre
document.getElementById("searchUser").addEventListener("input", (e) => {
    renderUsers(e.target.value);
});*/
