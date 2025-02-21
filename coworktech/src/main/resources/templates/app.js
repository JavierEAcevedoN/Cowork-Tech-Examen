
const API_URL = 'http://localhost:8080/api';

let currentView = 'spaces';
let spaces = [];
let reservations = [];

const spacesContainer = document.getElementById('spaces-container');
const reservationsContainer = document.getElementById('reservations-container');
const modal = document.getElementById('reservation-modal');
const reservationForm = document.getElementById('reservation-form');
const spaceTypeFilter = document.getElementById('space-type-filter');
const filterBtn = document.getElementById('filter-btn');
const navButtons = document.querySelectorAll('.nav-btn');

document.addEventListener('DOMContentLoaded', init);
filterBtn.addEventListener('click', filterSpaces);
reservationForm.addEventListener('submit', handleReservation);
document.querySelector('.close').addEventListener('click', () => modal.style.display = 'none');
navButtons.forEach(btn => btn.addEventListener('click', handleNavigation));

async function init() {
    await loadSpaces();
    await loadReservations();
}

function handleNavigation(e) {
    const view = e.target.dataset.view;
    currentView = view;
    

    navButtons.forEach(btn => btn.classList.remove('active'));
    e.target.classList.add('active');
    

    document.querySelectorAll('.view').forEach(v => v.classList.remove('active'));
    document.getElementById(`${view}-view`).classList.add('active');
}

async function loadSpaces() {
    try {
        const response = await fetch(`${API_URL}/spaces`);
        spaces = await response.json();
        renderSpaces(spaces);
    } catch (error) {
        console.error('Error al cargar espacios:', error);
        showError('No se pudieron cargar los espacios');
    }
}

function renderSpaces(spacesToRender) {
    spacesContainer.innerHTML = spacesToRender.map(space => `
        <div class="space-card">
            <span class="space-type">${space.type}</span>
            <h3>${space.name}</h3>
            <div class="space-info">
                <p>Capacidad: ${space.capacity} personas</p>
                <p>Estado: ${space.available ? 'Disponible' : 'No disponible'}</p>
            </div>
            ${space.available ? 
                `<button class="btn" onclick="openReservationModal('${space.id}')">
                    Reservar
                </button>` : 
                '<button class="btn" disabled>No disponible</button>'
            }
        </div>
    `).join('');
}

function filterSpaces() {
    const type = spaceTypeFilter.value;
    const filteredSpaces = type ? 
        spaces.filter(space => space.type === type) : 
        spaces;
    renderSpaces(filteredSpaces);
}

function openReservationModal(spaceId) {
    document.getElementById('space-id').value = spaceId;
    modal.style.display = 'block';
}

async function handleReservation(e) {
    e.preventDefault();
    
    const spaceId = document.getElementById('space-id').value;
    const date = document.getElementById('reservation-date').value;
    const startTime = document.getElementById('start-time').value;
    const endTime = document.getElementById('end-time').value;

    try {
        const response = await fetch(`${API_URL}/reservations`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                spaceId,
                date,
                startTime,
                endTime,
                status: 'PENDING'
            })
        });

        if (response.ok) {
            modal.style.display = 'none';
            await loadReservations();
            showSuccess('Reserva creada exitosamente');
        } else {
            throw new Error('Error al crear la reserva');
        }
    } catch (error) {
        console.error('Error:', error);
        showError('No se pudo crear la reserva');
    }
}

async function loadReservations() {
    try {
        const response = await fetch(`${API_URL}/reservations`);
        reservations = await response.json();
        renderReservations();
    } catch (error) {
        console.error('Error al cargar reservas:', error);
        showError('No se pudieron cargar las reservas');
    }
}

function renderReservations() {
    reservationsContainer.innerHTML = reservations.map(reservation => `
        <div class="reservation-card">
            <div>
                <h4>${getSpaceName(reservation.spaceId)}</h4>
                <p>Fecha: ${formatDate(reservation.date)}</p>
                <p>Horario: ${reservation.startTime} - ${reservation.endTime}</p>
                <p>Estado: ${reservation.status}</p>
            </div>
            ${reservation.status === 'PENDING' ? `
                <div>
                    <button class="btn" onclick="cancelReservation('${reservation.id}')">
                        Cancelar
                    </button>
                </div>
            ` : ''}
        </div>
    `).join('');
}

async function cancelReservation(reservationId) {
    try {
        const response = await fetch(`${API_URL}/reservations/${reservationId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                status: 'CANCELLED'
            })
        });

        if (response.ok) {
            await loadReservations();
            showSuccess('Reserva cancelada exitosamente');
        } else {
            throw new Error('Error al cancelar la reserva');
        }
    } catch (error) {
        console.error('Error:', error);
        showError('No se pudo cancelar la reserva');
    }
}

function getSpaceName(spaceId) {
    const space = spaces.find(s => s.id === spaceId);
    return space ? space.name : 'Espacio no encontrado';
}

function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString();
}

function showSuccess(message) {
    alert(message);
}

function showError(message) {
    alert(message);
}