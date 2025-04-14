// renderCalendario.js

const CLIENT_ID = '813036393361-4e3ab0khvp709s26a9ug0nkv03708tqh.apps.googleusercontent.com';
const API_KEY = 'AIzaSyCoamXsvr7JzW4WOnsJwXgxTES0SLHo9Z0';
const DISCOVERY_DOC = 'https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest';
const SCOPES = 'https://www.googleapis.com/auth/calendar.readonly';

function initClient() {
    gapi.client.init({
        apiKey: API_KEY,
        clientId: CLIENT_ID,
        discoveryDocs: [DISCOVERY_DOC],
        scope: SCOPES,
    }).then(() => {
        // Verifica si ya está logueado
        if (!gapi.auth2.getAuthInstance().isSignedIn.get()) {
            gapi.auth2.getAuthInstance().signIn();
        } else {
            cargarEventos();
        }

        // Escucha el cambio de estado de sesión
        gapi.auth2.getAuthInstance().isSignedIn.listen(isSignedIn => {
            if (isSignedIn) cargarEventos();
        });
    });
}

function cargarEventos() {
    gapi.client.calendar.events.list({
        calendarId: 'primary',
        timeMin: new Date().toISOString(),
        showDeleted: false,
        singleEvents: true,
        maxResults: 20,
        orderBy: 'startTime',
    }).then(response => {
        const eventos = response.result.items.map(event => ({
            title: event.summary,
            start: event.start.dateTime || event.start.date,
            end: event.end.dateTime || event.end.date,
        }));

        renderCalendario(eventos);
    });
}

function renderCalendario(eventos) {
    const calendarEl = document.getElementById('calendar');
    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'es',
        height: 'auto',
        events: eventos,
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay',
        },
    });
    calendar.render();
}

// Carga la API y lanza la inicialización
gapi.load('client:auth2', initClient);
