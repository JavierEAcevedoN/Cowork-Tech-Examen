package c3.coworktech.model;

import java.sql.Date;
import java.sql.Time;

import c3.coworktech.model.enums.estadoReservas;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idespacio", nullable = false)
    private Espacios idespacio;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Time horainicio;

    @Column(nullable = false)
    private Time horafin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private estadoReservas estado;

    public Reservas() {}

    public Reservas(Espacios idespacio, Date fecha, Time horainicio, Time horafin, estadoReservas estado) {
        this.idespacio = idespacio;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.estado = estado;
    }
}