let selectedService = "restaurante"; // Tipo de servicio inicial

// Función para obtener los proveedores desde la API
function getProveedores() {
    $.get("/proveedores", (data) => {
        console.log(data);
        renderProveedores(data);
    }).fail((error) => console.error("Error al obtener proveedores:", error));
}

// Función para renderizar proveedores
function renderProveedores(proveedores) {
    const sidebar = document.querySelector(".list-group");
    const searchInput = document.getElementById("searchProveedor");

    sidebar.innerHTML = `
        <div class="input-group my-2">
            <span class="input-group-text"><i class="fas fa-search"></i></span>
            <input type="text" class="form-control" placeholder="Buscar" oninput="filterProveedores(${JSON.stringify(proveedores)})" id="searchProveedor">
        </div>`;

    let filtered = proveedores.filter(p => p.serviceType.toLowerCase() === selectedService);
    const query = searchInput?.value?.toLowerCase() || "";

    if (query) {
        filtered = filtered.filter(p => p.serviceName.toLowerCase().includes(query));
    }

    if (filtered.length === 0) {
        const noResults = document.createElement("div");
        noResults.classList.add("list-group-item", "no-results");
        noResults.textContent = "No se encontraron resultados";
        sidebar.appendChild(noResults);
    } else {
        filtered.forEach(proveedor => {
            const btn = document.createElement("button");
            btn.classList.add("list-group-item", "list-group-item-action");
            btn.textContent = proveedor.serviceName;
            btn.addEventListener("click", () => loadMap(proveedor.location, proveedor.serviceName));
            sidebar.appendChild(btn);
        });
    }
}

// Función para filtrar proveedores
function filterProveedores(proveedores) {
    const searchInput = document.getElementById("searchProveedor");
    const query = searchInput?.value?.toLowerCase() || "";

    const filtered = proveedores.filter(p =>
        p.serviceType.toLowerCase() === selectedService &&
        p.serviceName.toLowerCase().includes(query)
    );

    const sidebar = document.querySelector(".list-group");
    sidebar.innerHTML = ""; // Limpiar la barra lateral

    if (filtered.length === 0) {
        const noResults = document.createElement("div");
        noResults.classList.add("list-group-item", "no-results");
        noResults.textContent = "No se encontraron resultados";
        sidebar.appendChild(noResults);
    } else {
        filtered.forEach(proveedor => {
            const btn = document.createElement("button");
            btn.classList.add("list-group-item", "list-group-item-action");
            btn.textContent = proveedor.serviceName;
            btn.addEventListener("click", () => loadMap(proveedor.location, proveedor.serviceName));
            sidebar.appendChild(btn);
        });
    }
}

// Función para cargar el mapa
function loadMap(location, serviceName) {
    const mapContainer = document.querySelector(".card.bg-secondary");
    const servicioNombreElement = document.getElementById("servicioNombre");

    if (servicioNombreElement) {
        servicioNombreElement.textContent = serviceName;
    }

    mapContainer.innerHTML = `
        <iframe width="100%" height="400" frameborder="0"
        style="border:0"
        src="https://www.google.com/maps?q=${location}&output=embed"
        allowfullscreen></iframe>
        <h5 class="card-title mt-2">${serviceName}</h5>`;
}

// Manejo de clicks en la barra de navegación para cambiar de servicio
document.querySelectorAll(".navbar-brand").forEach(item => {
    item.addEventListener("click", (event) => {
        event.preventDefault();

        if (event.target.textContent.includes("Restaurantes")) {
            selectedService = "restaurante";
        } else if (event.target.textContent.includes("Gimnasios")) {
            selectedService = "gimnasio";
        } else if (event.target.textContent.includes("Peluquerias")) {
            selectedService = "peluqueria";
        }

        getProveedores(); // Actualizar la lista de proveedores al cambiar servicio
    });
});

// Inicialización al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    getProveedores();
});
