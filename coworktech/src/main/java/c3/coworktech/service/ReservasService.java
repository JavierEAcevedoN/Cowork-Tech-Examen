package c3.coworktech.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.coworktech.exceptions.Reservas.ByEstadoException;
import c3.coworktech.exceptions.Reservas.ByFechaException;
import c3.coworktech.exceptions.Reservas.GetReservasException;
import c3.coworktech.exceptions.Reservas.PatchReservaException;
import c3.coworktech.exceptions.Reservas.SaveReservasException;
import c3.coworktech.model.Reservas;
import c3.coworktech.model.enums.estadoReservas;
import c3.coworktech.repository.ReservasRepository;
import jakarta.transaction.Transactional;

@Service
public class ReservasService {
    @Autowired
    private ReservasRepository reservasRepository;

    public List<Reservas> getReservas() {
        List<Reservas> reservas = reservasRepository.findAll();
        if (reservas.isEmpty()) {
            throw new GetReservasException("Reservas no content to show");
        }
        return reservas;
    }

    public List<Reservas> getReservasByEstado(estadoReservas estado) {
        List<Reservas> reservas = reservasRepository.findByEstado(estado);
        if (reservas.isEmpty()) {
            throw new ByEstadoException("Reservas by estado: " + estado.toString() + " no content to show");
        }
        return reservas;
    }

    public List<Reservas> getReservasByFecha(Date fechareserva) {
        List<Reservas> reservas = reservasRepository.findByFecha(fechareserva);
        if (reservas.isEmpty()) {
            throw new ByFechaException("Reservas by fecha: " + fechareserva + "no content to show");
        }
        return reservas;
    }

    public Reservas saveReservas(Reservas reservas) {
        try {
            return reservasRepository.save(reservas);
        } catch (Exception e) {
            throw new SaveReservasException("Bad request save reservas not found");
        }
    }

    @Transactional
    public void patchReservas(Long id, Reservas reservas) {
        int patchedRows = reservasRepository.patchReservas(reservas.getFecha(), reservas.getHorainicio(), reservas.getHorafin(), reservas.getEstado(), id);
        if (patchedRows == 0) {
            throw new PatchReservaException("Reserva to update not found");
        }
    }
}