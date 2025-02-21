package c3.coworktech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import c3.coworktech.model.Reservas;

public interface ReservasRepository extends JpaRepository<Reservas, Long> {
    
}