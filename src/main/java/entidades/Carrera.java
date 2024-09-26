// File: src/main/java/entidades/Carrera.java
package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarrera")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Cambiar 'duracion' a 'tipo' para coincidir con la base de datos
    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Relación con la entidad Facultad
    @ManyToOne
    @JoinColumn(name = "idfacultad", nullable = false)
    private Facultad facultad;

    // Constructor vacío
    public Carrera() {
    }

    // Constructor con los campos correctos
    public Carrera(Long id, String nombre, String tipo, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.facultad = facultad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    // Método toString
    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", facultad=" + facultad.getNombre() +
                '}';
    }
}
