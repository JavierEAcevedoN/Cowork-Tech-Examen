package c3.coworktech.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.coworktech.model.Reservas;
import c3.coworktech.model.enums.estadoReservas;
import c3.coworktech.service.ReservasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/reservas")
public class ReservasController {
    @Autowired
    private ReservasService reservasService;

    @GetMapping
    public List<Reservas> getReservas() {
        return reservasService.getReservas();
    }

    @GetMapping("/estado/{estado}")
    public List<Reservas> getReservasByEstado(@PathVariable estadoReservas estado) {
        return reservasService.getReservasByEstado(estado);
    }

    @GetMapping("/fecha/{fecha}")
    public List<Reservas> getReservasByFecha(@PathVariable Date fecha) {
        return reservasService.getReservasByFecha(fecha);
    }
    
    @PostMapping
    public Reservas saveReservas(@RequestBody Reservas reservas) {
        return reservasService.saveReservas(reservas);
    }

    @PatchMapping("/{id}")
    public void patchReservas(@PathVariable Long id, @RequestBody Reservas reservas) {
        reservasService.patchReservas(id, reservas);
    }
}