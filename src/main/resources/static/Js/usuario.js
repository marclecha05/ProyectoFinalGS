// Se ejecuta una función cuando el documento HTML ha sido cargado y está listo para ser manipulado
$(document).ready(function () {
    // Se realiza una solicitud AJAX a la URL "/user"
    $.ajax({
        url: "/user",
        method: "GET",
        dataType: "text" // Se lee como texto para evitar parseos automáticos de JSON
    })
        .done((data) => {
            // Se ejecuta cuando la solicitud AJAX ha sido exitosa
            console.log("Contenido de la respuesta:", data);
            try {
                // Se intenta parsear la respuesta como JSON
                const jsonData = JSON.parse(data);
                console.log("JSON parseado:", jsonData);
                // Se actualizan los textos de los elementos HTML con id "username" y "email"
                $("#username").text(`${jsonData[0]}`);
                $("#email").text(`${jsonData[1]}`);
            } catch (e) {
                // Se ejecuta si hay un error al parsear la respuesta como JSON
                console.error("Error al parsear JSON:", e);
            }
        })
        .fail((jqXHR) => {
            // Se ejecuta si la solicitud AJAX ha fallado
            console.error("Error en la solicitud:", jqXHR.responseText);
        });
})
