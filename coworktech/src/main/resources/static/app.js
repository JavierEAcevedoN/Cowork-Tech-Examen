const BASE_URL = 'http://localhost:8081/api';
const API_ESPACIOS = BASE_URL + '/espacios';
const API_RESERVAS = BASE_URL + '/reservas';

let currentView = 'spaces';
let spaces = [];
let reservations = [];
let currentReservationId = null;

const spacesContainer = document.getElementById('spaces-container');
const reservationsContainer = document.getElementById('reservations-container');
const modal = document.getElementById('reservation-modal');
const reservationForm = document.getElementById('reservation-form');
const spaceTypeFilter = document.getElementById('space-type-filter');
const filterBtn = document.getElementById('filter-btn');
const navButtons = document.querySelectorAll('.nav-btn');

const spacesModal = document.getElementById('spaces-modal');
const spaceForm = document.getElementById('space-form');
const confirmModal = document.getElementById('confirm-modal');
const newSpaceBtn = document.getElementById('new-space-btn');
const confirmDeleteBtn = document.getElementById('confirm-delete-btn');

const availabilityFilter = document.getElementById('availability-filter');
const filterSpacesBtn = document.getElementById('filter-spaces-btn');
const reservationFilterDate = document.getElementById('reservation-filter-date');
const reservationStatusFilter = document.getElementById('reservation-status-filter');
const filterReservationsBtn = document.getElementById('filter-reservations-btn');
const clearFiltersBtn = document.getElementById('clear-filters-btn');
const newReservationBtn = document.getElementById('new-reservation-btn');

let currentSpaceId = null;

document.addEventListener('DOMContentLoaded', init);
filterSpacesBtn.addEventListener('click', filterSpaces);
filterReservationsBtn.addEventListener('click', filterReservations);
clearFiltersBtn.addEventListener('click', clearReservationFilters);
reservationForm.addEventListener('submit', handleReservation);
document.querySelector('.close').addEventListener('click', () => modal.style.display = 'none');
navButtons.forEach(btn => btn.addEventListener('click', handleNavigation));

newSpaceBtn.addEventListener('click', () => openSpaceModal);
newReservationBtn.addEventListener('click', openReservationModal);
spaceForm.addEventListener('submit', handleSpaceSubmit);
document.querySelectorAll('.close, .btn-secondary[data-modal]').forEach(element => {
    element.addEventListener('click', (e) => {
        const modalId = e.target.dataset.modal;
        document.getElementById(modalId).style.display = 'none';
    });
});

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
        const response = await fetch(API_ESPACIOS);
        spaces = await response.json();
        renderSpaces(spaces);
    } catch (error) {
        console.error('Error al cargar espacios:', error);
        showError('No se pudieron cargar los espacios');
    }
}

function openSpaceModal(spaceId = null) {
    const modalTitle = document.getElementById('space-modal-title');
    const form = document.getElementById('space-form');
    
    if (spaceId) {
    
        const space = spaces.find(s => s.id === spaceId);
        if (!space) return;
        
        modalTitle.textContent = 'Editar Espacio';
        document.getElementById('space-form-id').value = space.id;
        document.getElementById('space-name').value = space.nombre;
        document.getElementById('space-type').value = space.tipo;
        document.getElementById('space-capacity').value = space.capacidadmax;
        document.getElementById('space-available').value = space.disponibilidad;
        currentSpaceId = spaceId;
    } else {
    
        modalTitle.textContent = 'Nuevo Espacio';
        form.reset();
        document.getElementById('space-form-id').value = '';
        currentSpaceId = null;
    }
    
    spacesModal.style.display = 'block';
}

async function handleSpaceSubmit(e) {
    e.preventDefault();
    
    const spaceData = {
        nombre: document.getElementById('space-name').value,
        tipo: document.getElementById('space-type').value,
        capacidadmax: parseInt(document.getElementById('space-capacity').value),
        disponibilidad: document.getElementById('space-available').value
    };

    try {
        let response;
        if (currentSpaceId) {
        
            response = await fetch(`${API_ESPACIOS}/${currentSpaceId}`, {
                method: 'PATCH',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(spaceData)
            });
        } else {
        
            response = await fetch(`${API_ESPACIOS}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(spaceData)
            });
        }

        if (response.ok) {
            await loadSpaces();
            spacesModal.style.display = 'none';
            showSuccess(currentSpaceId ? 'Espacio actualizado exitosamente' : 'Espacio creado exitosamente');
        } else {
            throw new Error('Error en la operación');
        }
    } catch (error) {
        console.error('Error:', error);
        showError('No se pudo guardar el espacio');
    }
}

async function deleteSpace(spaceId) {
    try {
        const response = await fetch(`${API_ESPACIOS}/${spaceId}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            await loadSpaces();
            confirmModal.style.display = 'none';
            showSuccess('Espacio eliminado exitosamente');
        } else {
            throw new Error('Error al eliminar el espacio');
        }
    } catch (error) {
        console.error('Error:', error);
        showError('No se pudo eliminar el espacio');
    }
}

function confirmDelete(spaceId) {
    currentSpaceId = spaceId;
    confirmModal.style.display = 'block';
    
    confirmDeleteBtn.onclick = () => deleteSpace(spaceId);
}

function renderSpaces(spacesToRender) {
    spacesContainer.innerHTML = spacesToRender.map(space => `
        <div class="space-card">
            <div class="space-card-header">
                <span class="space-type">${space.tipo}</span>
                <div class="space-actions">
                    <button class="btn btn-icon" onclick="openSpaceModal(${space.id})" title="Editar">
                        ✏️
                    </button>
                    <button class="btn btn-icon delete" onclick="confirmDelete(${space.id})" title="Eliminar">
                        Eliminar
                    </button>
                </div>
            </div>
            <h3>${space.nombre}</h3>
            <div class="space-info">
                <p>Capacidad: ${space.capacidadmax} personas</p>
                <p>Estado: ${space.disponibilidad === 'Activo' ? 'Disponible' : 'No disponible'}</p>
            </div>
            ${space.available === 'Activo' ? 
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
    const availability = availabilityFilter.value;
    
    let filteredSpaces = [...spaces];

    if (type) {
        filteredSpaces = filteredSpaces.filter(space => space.tipo === type);
    }

    if (availability) {
        filteredSpaces = filteredSpaces.filter(space => {
            if (availability === 'Activo') return space.disponibilidad;
            if (availability === 'Inactivo') return !space.disponibilidad;
            return true;
        });
    }

    renderSpaces(filteredSpaces);
}

function openReservationModal(spaceId = null, reservationId = null) {
    const modalTitle = document.getElementById('reservation-modal-title');
    const form = document.getElementById('reservation-form');
    if (reservationId) {
    
        const reservation = reservations.find(r => r.id === reservationId);
        if (!reservation) return;
        
        modalTitle.textContent = 'Editar Reserva';
        document.getElementById('reservation-form-id').value = reservation.id;
        document.getElementById('space-id').value = reservation.spaceId;
        document.getElementById('reservation-date').value = new Date(reservation.fecha).toISOString().split('T')[0];
        document.getElementById('start-time').value = reservation.horainicio;
        document.getElementById('end-time').value = reservation.horafin;
        currentReservationId = reservationId;
    } else {
    
        modalTitle.textContent = 'Nueva Reserva';
        form.reset();
        document.getElementById('reservation-form-id').value = '';
        document.getElementById('space-id').value = spaceId;
        currentReservationId = null;
    }
    
    modal.style.display = 'block';
}

async function handleReservation(e) {
    e.preventDefault();
    
    const reservationData = {
        spaceId: document.getElementById('space-id').value,
        fecha: document.getElementById('reservation-date').value,
        horainicio: document.getElementById('start-time').value,
        horafin: document.getElementById('end-time').value,
        estado: currentReservationId ? 'Pendiente' : 'Pendiente'
    };

    try {
        let response;
        if (currentReservationId) {
        
            response = await fetch(`${API_RESERVAS}/${currentReservationId}`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reservationData)
            });
        } else {
        
            response = await fetch(API_RESERVAS, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reservationData)
            });
        }

        if (response.ok) {
            modal.style.display = 'none';
            await loadReservations();
            showSuccess(currentReservationId ? 'Reserva actualizada exitosamente' : 'Reserva creada exitosamente');
        } else {
            throw new Error('Error en la operación');
        }
    } catch (error) {
        console.error('Error:', error);
        showError('No se pudo guardar la reserva');
    }
}




async function loadReservations() {
    try {
        const response = await fetch(API_RESERVAS);
        reservations = await response.json();
        renderReservations();
    } catch (error) {
        console.error('Error al cargar reservas:', error);
        showError('No se pudieron cargar las reservas');
    }
}

function filterReservations() {
    debugger;
    const filterDate = reservationFilterDate.value;
    const filterStatus = reservationStatusFilter.value;

    let filteredReservations = [...reservations];

    if (filterDate) {
        filteredReservations = filteredReservations.filter(reservation => {
        
            const reservationDate = new Date(reservation.fecha).toISOString().split('T')[0];
            return reservationDate === filterDate;
        });
    }

    if (filterStatus) {
        filteredReservations = filteredReservations.filter(reservation => 
            reservation.estado === filterStatus
        );
    }

    renderReservations(filteredReservations);
}

function clearReservationFilters() {
    reservationFilterDate.value = '';
    reservationStatusFilter.value = '';
    renderReservations(reservations);
}

function renderReservations(reservationsToRender = reservations) {
    reservationsContainer.innerHTML = reservationsToRender.map(reservation => `
        <div class="reservation-card">
            <div>
                <h4>${getSpaceName(reservation.id)}</h4>
                <p>Fecha: ${formatDate(reservation.fecha)}</p>
                <p>Horario: ${reservation.horainicio} - ${reservation.horafin}</p>
                <p>Estado: ${reservation.estado}</p>
            </div>
            <div class="reservation-actions">
                <button class="btn btn-icon" onclick="openReservationModal(null, ${reservation.id})" title="Editar">
                            Editar
                    </button>
                ${reservation.status === 'Pendiente' ? `
                    <button class="btn btn-icon delete" onclick="cancelReservation(${reservation.id})" title="Cancelar">
                        Cancelar Reserva
                    </button>
                ` : ''}
            </div>
        </div>
    `).join('');

    if (reservationsToRender.length === 0) {
        reservationsContainer.innerHTML = `
            <div class="no-results">
                <p>No se encontraron reservas con los filtros seleccionados</p>
            </div>
        `;
    }
}

async function cancelReservation(reservationId) {
    try {
        const response = await fetch(`${API_RESERVAS}/${reservationId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                status: 'Cancelada'
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
    return space ? space.nombre : 'Espacio no encontrado';
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