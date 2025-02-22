package c3.coworktech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import c3.coworktech.exceptions.Espacios.ByDisponibilidad;
import c3.coworktech.exceptions.Espacios.ByTipoException;
import c3.coworktech.exceptions.Espacios.DeleteById;
import c3.coworktech.exceptions.Espacios.GetEspaciosException;
import c3.coworktech.exceptions.Espacios.PatchEspacios;
import c3.coworktech.exceptions.Espacios.SaveEspacios;
import c3.coworktech.model.Espacios;
import c3.coworktech.model.enums.disponibilidad;
import c3.coworktech.model.enums.tipoEspacio;
import c3.coworktech.repository.EspaciosRepository;
import jakarta.transaction.Transactional;

@Service
public class EspaciosService {
    @Autowired
    private EspaciosRepository espaciosRepository;

    public List<Espacios> getEspacios() {
        List<Espacios> espacios = espaciosRepository.findAll();
        if (espacios.isEmpty()) {
            throw new GetEspaciosException("Espacios no content to show");
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByTipo(tipoEspacio tipo) {
        List<Espacios> espacios = espaciosRepository.findByTipo(tipo);
        if (espacios.isEmpty()) {
            throw new ByTipoException("Espacios by tipo: " + tipo.toString() + "not found");
        }
        return espacios;
    }

    public List<Espacios> getEspaciosByDisponibilidad(disponibilidad disponibilidad) {
        List<Espacios> espacios = espaciosRepository.findByDisponibilidad(disponibilidad);
        if (espacios.isEmpty()) {
            throw new ByDisponibilidad("Espacios by disponibilidad: " + disponibilidad.toString() + "not found");
        }
        return espacios;
    }

    public Espacios saveEspacios(Espacios espacios) {
        try {
            return espaciosRepository.save(espacios);
        } catch (Exception e) {
            throw new SaveEspacios("Bad Request to save an espacio");
        }
    }

    @Transactional
    public void patchEspacios(Long id, Espacios espacios) {
        int patchedRows = espaciosRepository.patchEspacios(espacios.getNombre(), espacios.getTipo(), espacios.getCapacidadmax(), espacios.getDisponibilidad(), id);
        if (patchedRows == 0) {
            throw new PatchEspacios("Bad request to patch espacio");
        }
    }

    public void deleteEspaciosById(Long id) {
        try {
            espaciosRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteById("Espacio not fount to delete");
        }
    }
}