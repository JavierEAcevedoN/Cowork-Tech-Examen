package c3.coworktech.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
            // error
        }
        return reservas;
    }

    public List<Reservas> getReservasByEstado(estadoReservas estado) {
        List<Reservas> reservas = reservasRepository.findByEstado(estado);
        if (reservas.isEmpty()) {
            // error
        }
        return reservas;
    }

    public List<Reservas> getReservasByFecha(Date fechareserva) {
        List<Reservas> reservas = reservasRepository.findByFecha(fechareserva);
        if (reservas.isEmpty()) {
            // error
        }
        return reservas;
    }

    public Reservas saveReservas(Reservas reservas) {
        try {
            return reservasRepository.save(reservas);
        } catch (Exception e) {
            // error
            throw new RuntimeException();
        }
    }

    @Transactional
    public void patchReservas(Long id, Reservas reservas) {
        int patchedRows = reservasRepository.patchEspacios(reservas.getFecha(), reservas.getHorainicio(), reservas.getHorafin(), reservas.getEstado(), id);
        if (patchedRows == 0) {
            // error
        }
    }
}