function mostrarMapa(enlace) {
    // Extraer el parámetro de la ubicación de Google Maps
    const match = enlace.match(/@(-?\d+\.\d+),(-?\d+\.\d+)/);
    if (!match) {
        console.error("El enlace proporcionado no es válido.");
        return;
    }

    const lat = match[1];
    const lng = match[2];

    // Crear el iframe con el mapa embebido
    const iframe = document.createElement("iframe");
    iframe.width = "600";
    iframe.height = "450";
    iframe.style.border = "0";
    iframe.loading = "lazy";
    iframe.allowFullscreen = true;
    iframe.referrerPolicy = "no-referrer-when-downgrade";
    iframe.src = `https://www.google.com/maps/embed/v1/view?key=TU_CLAVE_API&center=${lat},${lng}&zoom=15`;

    // Insertar el iframe en el contenedor
    document.getElementById("mapa").innerHTML = "";
    document.getElementById("mapa").appendChild(iframe);
}

// Ejemplo de uso
const enlaceGoogleMaps = "https://www.google.com/maps/place/Eiffel+Tower/@48.8588443,2.2943506,17z";
mostrarMapa(enlaceGoogleMaps);