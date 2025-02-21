package c3.coworktech.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c3.coworktech.model.Espacios;
import c3.coworktech.model.enums.disponibilidad;
import c3.coworktech.model.enums.tipoEspacio;
import c3.coworktech.service.EspaciosService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/espacios")
public class EspaciosController {
    @Autowired
    private EspaciosService espaciosService;

    @GetMapping
    public List<Espacios> getEspacios() {
        return espaciosService.getEspacios();
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<Espacios> getEspaciosByTipo(@PathVariable tipoEspacio tipo) {
        return espaciosService.getEspaciosByTipo(tipo);
    }
    
    @GetMapping("/disponibilidad/{disponibilidad}")
    public List<Espacios> getEspaciosByDisponibilidad(@PathVariable disponibilidad disponibilidad) {
        return espaciosService.getEspaciosByDisponibilidad(disponibilidad);
    }

    @PostMapping
    public Espacios saveEspacios(@RequestBody Espacios espacios) {
        return espaciosService.saveEspacios(espacios);
    }
    
    @PatchMapping("/{id}")
    public void patchEspacios(@PathVariable Long id, @RequestBody Espacios espacios) {
        espaciosService.patchEspacios(id, espacios);
    }

    @DeleteMapping("/{id}")
    public void deleteEspacios(@PathVariable Long id) {
        espaciosService.deleteEspaciosById(id);;
    }
}