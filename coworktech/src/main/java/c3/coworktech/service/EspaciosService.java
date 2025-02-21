package c3.coworktech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.coworktech.model.Espacios;
import c3.coworktech.repository.EspaciosRepository;

@Service
public class EspaciosService {
    @Autowired
    private EspaciosRepository espaciosRepository;

    public List<Espacios> getEspacios() {
        List<Espacios> espacios = espaciosRepository.findAll();
        if (espacios.isEmpty()) {
            
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByTipo(String tipo) {
        List<Espacios> espacios = espaciosRepository.findByTipo(tipo);
        if (espacios.isEmpty()) {
            
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByDisponibilidad(String disponibilidad) {
        List<Espacios> espacios = espaciosRepository.findByTipo(disponibilidad);
        if (espacios.isEmpty()) {
            
        }
        return espacios;
    }

    public Espacios saveEspacios(Espacios espacios) {
        return espaciosRepository.save(espacios);
    }
}