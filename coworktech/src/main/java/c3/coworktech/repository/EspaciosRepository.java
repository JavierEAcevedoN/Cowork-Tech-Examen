package c3.coworktech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.coworktech.model.Espacios;
import jakarta.transaction.Transactional;
import c3.coworktech.model.enums.tipoEspacio;
import c3.coworktech.model.enums.disponibilidad;

public interface EspaciosRepository extends JpaRepository<Espacios, Long> {
    List<Espacios> findByTipo(tipoEspacio tipo);
    List<Espacios> findByDisponibilidad(disponibilidad disponibilidad);

    @Modifying
    @Transactional
    @Query("UPDATE Espacios e SET " +
        "e.nombre = CASE WHEN :nombre IS NOT NULL THEN :nombre ELSE e.nombre END, " +
        "e.tipo = CASE WHEN :tipo IS NOT NULL THEN :tipo ELSE e.tipo END, " +
        "e.capacidadmax = CASE WHEN :capacidadmax IS NOT NULL THEN :capacidadmax ELSE e.capacidadmax END, " +
        "e.disponibilidad = CASE WHEN :disponibilidad IS NOT NULL THEN :disponibilidad ELSE e.disponibilidad END " +
        "WHERE e.id = :id")
    int patchEspacios(@Param("nombre") String nombre,
        @Param("tipo") tipoEspacio tipo,
        @Param("capacidadmax") Integer capacidadmax,
        @Param("disponibilidad") disponibilidad disponibilidad,
        @Param("id") Long id);
}