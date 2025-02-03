package org.example.entities;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Representación de un animal en el sistema. La clase {@link Animales} mapea la entidad
 * "animales" en la base de datos y almacena información sobre cada animal,
 * como su nombre, tipo, edad, detalles y estado. Además, esta clase tiene una relación
 * con la clase {@link Familia} que representa la familia a la que pertenece el animal.
 */
@Entity
@Table(name = "animales")
public class Animales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private int años;
    private String detalles;

    @Enumerated(EnumType.STRING)
    private Estado estado;  // Aquí definimos el estado del animal como un Enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id")
    private Familia familia;  // Relación con la clase Familia

    /**
     * Constructor vacío de la clase Animales.
     */
    public Animales() {}

    /**
     * Constructor con parámetros para inicializar un animal.
     *
     * @param nombre Nombre del animal.
     * @param tipo Tipo de animal.
     * @param años Edad en años del animal.
     * @param detalles Detalles adicionales sobre el animal.
     */
    public Animales(String nombre, String tipo, int años, String detalles) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.años = años;
        this.detalles = detalles;
    }

    /**
     * Obtiene el identificador único del animal.
     *
     * @return El identificador del animal.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del animal.
     *
     * @param id El identificador a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del animal.
     *
     * @return El nombre del animal.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del animal.
     *
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo del animal.
     *
     * @return El tipo del animal.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del animal.
     *
     * @param tipo El tipo a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la edad del animal en años.
     *
     * @return La edad del animal en años.
     */
    public int getAños() {
        return años;
    }

    /**
     * Establece la edad del animal en años.
     *
     * @param años La edad a asignar.
     */
    public void setAños(int años) {
        this.años = años;
    }

    /**
     * Obtiene los detalles adicionales sobre el animal.
     *
     * @return Los detalles del animal.
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * Establece los detalles adicionales sobre el animal.
     *
     * @param detalles Los detalles a asignar.
     */
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    /**
     * Obtiene el estado del animal.
     *
     * @return El estado del animal como un valor {@link Estado}.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del animal a partir de un valor {@link String}.
     * Si el valor no es un estado válido, lanza una excepción {@link IllegalArgumentException}.
     *
     * @param estado El estado del animal en formato {@link String}.
     * @throws IllegalArgumentException Si el valor no corresponde a un estado válido.
     */
    public void setEstado(String estado) {
        try {
            this.estado = Estado.valueOf(estado.toUpperCase());  // Convierte el valor de estado a su valor Enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado no válido. Valores válidos son: " + Estado.values());
        }
    }

    /**
     * Obtiene la familia a la que pertenece el animal.
     *
     * @return La familia del animal.
     */
    public Familia getFamilia() {
        return familia;
    }

    /**
     * Establece la familia a la que pertenece el animal.
     *
     * @param familia La familia a asignar.
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    /**
     * Obtiene la especie del animal, la cual está representada por el tipo.
     *
     * @return El tipo del animal que se asume como su especie.
     */
    public String getEspecie() {
        return tipo;  // Asumimos que el campo tipo es especie
    }

    /**
     * Establece la especie del animal, la cual se asigna al campo tipo.
     *
     * @param especie La especie a asignar.
     */
    public void setEspecie(String especie) {
        this.tipo = especie;  // Asignamos tipo como especie
    }

    /**
     * Devuelve una representación en forma de cadena del animal.
     *
     * @return Una cadena con los detalles del animal, como nombre, tipo, edad y detalles.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Tipo: " + tipo + ", Años: " + años + ", Detalles: " + detalles;
    }

    public int getEdad() {
        return this.años;
    }

    public void setEdad(int i) {
        this.años = i;
    }
}
