package c3.coworktech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "espacios")
public class Espacios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "ENUM", nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer capacidadmax;

    @Column(columnDefinition = "ENUM", nullable = false)
    private String disponibilidad;

    public Espacios() {}

    public Espacios(Long id, String nombre, String tipo, Integer capacidadmax, String disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadmax = capacidadmax;
        this.disponibilidad = disponibilidad;
    }
}