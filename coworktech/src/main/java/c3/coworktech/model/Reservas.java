package c3.coworktech.model;

import java.sql.Date;
import java.sql.Time;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Date fecha;

    @Column(nullable = false)
    private Time horainicio;

    @Column(nullable = false)
    private Time horafin;

    @Column(nullable = false)
    private String estado;

    public Reservas() {}

    public Reservas(Long idespacio, Date fecha, Time horainicio, Time horafin, String estado) {
        this.idespacio = idespacio;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.estado = estado;
    }
}