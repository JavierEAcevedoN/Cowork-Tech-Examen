package c3.coworktech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import c3.coworktech.model.Espacios;

public interface EspaciosRepository extends JpaRepository<Espacios, Long> {
    public List<Espacios> findByTipo(String tipo);
    public List<Espacios> findByDisponibilidad(String disponibilidad);
}