package c3.coworktech.model;

import java.sql.Timestamp;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idespacio", nullable = false)
    private Long idespacio;

    @Column(nullable = false)
    private Timestamp fechaservicioinicio;

    @Column(nullable = false)
    private Timestamp fechaserviciofin;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private estadoReservas estado;

    public Reservas() {}

    public Reservas(Long id, Long idespacio, Timestamp fechaservicioinicio, Timestamp fechaserviciofin, estadoReservas estado) {
        this.id = id;
        this.idespacio = idespacio;
        this.fechaservicioinicio = fechaservicioinicio;
        this.fechaserviciofin = fechaserviciofin;
        this.estado = estado;
    }
}