package c3.coworktech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.coworktech.model.Espacios;
import c3.coworktech.repository.EspaciosRepository;
import jakarta.transaction.Transactional;

@Service
public class EspaciosService {
    @Autowired
    private EspaciosRepository espaciosRepository;

    public List<Espacios> getEspacios() {
        List<Espacios> espacios = espaciosRepository.findAll();
        if (espacios.isEmpty()) {
            // error
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByTipo(String tipo) {
        List<Espacios> espacios = espaciosRepository.findByTipo(tipo);
        if (espacios.isEmpty()) {
            // error
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByDisponibilidad(String disponibilidad) {
        List<Espacios> espacios = espaciosRepository.findByTipo(disponibilidad);
        if (espacios.isEmpty()) {
            // error
        }
        return espacios;
    }

    public Espacios saveEspacios(Espacios espacios) {
        try {
            return espaciosRepository.save(espacios);
        } catch (Exception e) {
            // error
            throw new RuntimeException();
        }
    }

    @Transactional
    public void patchEspacios(Long id, Espacios espacios) {
        int patchedRows = espaciosRepository.patchEspacios(espacios.getNombre(), espacios.getTipo(), espacios.getCapacidadmax(), espacios.getDisponibilidad(), id);
        if (patchedRows == 0) {
            // error
        }
    }

    public void deleteEspaciosById(Long id) {
        try {
            espaciosRepository.deleteById(id);
        } catch (Exception e) {
            // error
            throw new RuntimeException();
        }
    }
}