package c3.coworktech.model;

import c3.coworktech.model.enums.disponibilidad;
import c3.coworktech.model.enums.tipoEspacio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "espacios")
public class Espacios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoEspacio tipo;

    @Column(nullable = false)
    private Integer capacidadmax;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private disponibilidad disponibilidad;

    public Espacios() {}

    public Espacios(Long id, String nombre, tipoEspacio tipo, Integer capacidadmax,
            disponibilidad disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadmax = capacidadmax;
        this.disponibilidad = disponibilidad;
    }
}