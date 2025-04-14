/*const daysTag = document.querySelector(".days"),
    currentDate = document.querySelector(".current-date"),
    prevNextIcon = document.querySelectorAll(".icons span");
// getting new date, current year and month
let date = new Date(),
    currYear = date.getFullYear(),
    currMonth = date.getMonth();
// storing full name of all months in array
const months = ["January", "February", "March", "April", "May", "June", "July",
    "August", "September", "October", "November", "December"];
const renderCalendar = () => {
    let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(), // getting first day of month
        lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(), // getting last date of month
        lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(), // getting last day of month
        lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate(); // getting last date of previous month
    let liTag = "";
    for (let i = firstDayofMonth; i > 0; i--) { // creating li of previous month last days
        liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
    }
    for (let i = 1; i <= lastDateofMonth; i++) { // creating li of all days of current month
        // adding active class to li if the current day, month, and year matched
        let isToday = i === date.getDate() && currMonth === new Date().getMonth()
        && currYear === new Date().getFullYear() ? "active" : "";
        liTag += `<li class="${isToday}">${i}</li>`;
    }
    for (let i = lastDayofMonth; i < 6; i++) { // creating li of next month first days
        liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`
    }
    currentDate.innerText = `${months[currMonth]} ${currYear}`; // passing current mon and yr as currentDate text
    daysTag.innerHTML = liTag;
}
renderCalendar();
prevNextIcon.forEach(icon => { // getting prev and next icons
    icon.addEventListener("click", () => { // adding click event on both icons
        // if clicked icon is previous icon then decrement current month by 1 else increment it by 1
        currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;
        if(currMonth < 0 || currMonth > 11) { // if current month is less than 0 or greater than 11
            // creating a new date of current year & month and pass it as date value
            date = new Date(currYear, currMonth, new Date().getDate());
            currYear = date.getFullYear(); // updating current year with new date year
            currMonth = date.getMonth(); // updating current month with new date month
        } else {
            date = new Date(); // pass the current date as date value
        }
        renderCalendar(); // calling renderCalendar function
    });
});*/
document.addEventListener("DOMContentLoaded", function () {
    const CLIENT_ID = "813036393361-4e3ab0khvp709s26a9ug0nkv03708tqh.apps.googleusercontent.com"; // Reemplaza con tu Client ID de Google Cloud
    const API_KEY = process.env.API_KEY; // Reemplaza con tu API Key de Google Cloud
    const SCOPES = "https://www.googleapis.com/auth/calendar.readonly";
    const DISCOVERY_DOC = "https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest";

    const authStatus = document.getElementById("auth-status");
    const calendarEl = document.getElementById("calendar");
    let calendar;

    // Inicializar la API de Google automáticamente con la sesión activa
    function handleClientLoad() {
        gapi.load("client:auth2", initClient);
    }

    function initClient() {
        gapi.client
            .init({
                apiKey: API_KEY,
                clientId: CLIENT_ID,
                discoveryDocs: [DISCOVERY_DOC],
                scope: SCOPES,
            })
            .then(() => {
                const GoogleAuth = gapi.auth2.getAuthInstance();
                if (GoogleAuth.isSignedIn.get()) {
                    // Si ya está autenticado, carga el calendario
                    loadCalendarEvents();
                    authStatus.textContent = "Autenticado";
                    authStatus.className = "auth-status authenticated";
                } else {
                    authStatus.textContent = "No autenticado";
                    authStatus.className = "auth-status unauthenticated";
                }
            })
            .catch((error) => {
                console.error("Error inicializando la API:", error);
            });
    }

    // Cargar eventos del calendario
    function loadCalendarEvents() {
        gapi.client.calendar.events
            .list({
                calendarId: "primary",
                timeMin: new Date().toISOString(),
                singleEvents: true,
                orderBy: "startTime",
            })
            .then((response) => {
                const events = response.result.items.map((event) => ({
                    title: event.summary,
                    start: event.start.dateTime || event.start.date,
                    end: event.end.dateTime || event.end.date,
                }));

                // Renderizar el calendario con los eventos
                if (calendar) calendar.destroy(); // Evitar duplicados
                calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: "dayGridMonth",
                    events: events,
                });
                calendar.render();
            })
            .catch((error) => {
                console.error("Error al cargar eventos:", error);
            });
    }

    // Iniciar la carga de la API de Google
    handleClientLoad();
});


