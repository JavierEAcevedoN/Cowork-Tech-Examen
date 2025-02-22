package c3.coworktech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import c3.coworktech.model.Reservas;
import c3.coworktech.model.enums.estadoReservas;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.sql.Time;


public interface ReservasRepository extends JpaRepository<Reservas, Long> {
    List<Reservas> findByEstado(estadoReservas estado);
    List<Reservas> findByFecha(Date fecha);

    @Modifying
    @Transactional
    @Query("UPDATE Reservas r SET r.fecha = CASE WHEN :fecha IS NOT NULL THEN :fecha ELSE r.fecha END, r.horainicio = CASE WHEN :horainicio IS NOT NULL THEN :horainicio ELSE r.horainicio END, r.horafin = CASE WHEN :horafin IS NOT NULL THEN :horafin ELSE r.horafin END, r.estado = CASE WHEN :estado IS NOT NULL THEN :estado ELSE r.estado END WHERE r.id = :id")
    int patchReservas(@Param("fecha") Date fecha,
        @Param("horainicio") Time horainicio,
        @Param("horafin") Time horafin,
        @Param("estado") estadoReservas estado,
        @Param("id") Long id);
}