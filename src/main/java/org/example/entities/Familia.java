package org.example.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una familia en el sistema, con detalles sobre su nombre, edad, ciudad
 * y los animales que han acogido. La clase establece una relación de uno a muchos
 * con la entidad {@link Animales}, donde una familia puede acoger varios animales.
 */
@Entity
@Table(name = "familias")
public class Familia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String ciudad;

    /**
     * Relación con la entidad {@link Animales}, donde una familia puede acoger múltiples animales.
     * La lista de animales acogidos se mantiene con la opción de cascada para las operaciones.
     */
    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animales> animalesAcogidos = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Familia() {}

    /**
     * Constructor con parámetros para inicializar una nueva familia con el nombre, edad y ciudad.
     *
     * @param nombre Nombre de la familia.
     * @param edad Edad de la familia.
     * @param ciudad Ciudad de residencia de la familia.
     */
    public Familia(String nombre, int edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    // Métodos Getters y Setters

    /**
     * Obtiene el identificador único de la familia.
     *
     * @return El identificador de la familia.
     */
    public Long obtenerId() {
        return id;
    }

    /**
     * Asigna un identificador único a la familia.
     *
     * @param id El identificador de la familia.
     */
    public void asignarId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la familia.
     *
     * @return El nombre de la familia.
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre a la familia. Si el nombre es nulo o vacío, lanza una excepción.
     *
     * @param nombre El nombre de la familia.
     * @throws IllegalArgumentException Si el nombre es nulo o vacío.
     */
    public void asignarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad de la familia.
     *
     * @return La edad de la familia.
     */
    public int obtenerEdad() {
        return edad;
    }

    /**
     * Asigna la edad a la familia. Si la edad es menor o igual a cero, lanza una excepción.
     *
     * @param edad La edad de la familia.
     * @throws IllegalArgumentException Si la edad es menor o igual a cero.
     */
    public void asignarEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser un valor positivo.");
        }
        this.edad = edad;
    }

    /**
     * Obtiene la ciudad de residencia de la familia.
     *
     * @return La ciudad de la familia.
     */
    public String obtenerCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad a la familia. Si la ciudad es nula o vacía, lanza una excepción.
     *
     * @param ciudad La ciudad de la familia.
     * @throws IllegalArgumentException Si la ciudad es nula o vacía.
     */
    public void asignarCiudad(String ciudad) {
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede estar vacía.");
        }
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la lista de animales acogidos por la familia. Retorna una copia de la lista
     * para evitar modificaciones directas sobre la lista original.
     *
     * @return Una copia de la lista de animales acogidos.
     */
    public List<Animales> obtenerAnimalesAcogidos() {
        return new ArrayList<>(animalesAcogidos);
    }

    /**
     * Agrega un animal a la lista de animales acogidos por la familia.
     *
     * @param animal El animal a agregar.
     */
    public void agregarAnimal(Animales animal) {
        if (animal != null) {
            animalesAcogidos.add(animal);
        }
    }

    /**
     * Elimina un animal de la lista de animales acogidos por la familia.
     *
     * @param animal El animal a eliminar.
     */
    public void removerAnimal(Animales animal) {
        if (animal != null) {
            animalesAcogidos.remove(animal);
        }
    }

    /**
     * Obtiene el número total de animales acogidos por la familia.
     *
     * @return El número total de animales acogidos.
     */
    public int obtenerNumeroDeAnimales() {
        return animalesAcogidos.size();
    }

    /**
     * Devuelve una representación en cadena de texto de la familia, incluyendo su nombre, edad, ciudad
     * y el número total de animales acogidos.
     *
     * @return Una cadena representando la familia.
     */
    @Override
    public String toString() {
        return "Familia [Nombre: " + nombre + ", Edad: " + edad + ", Ciudad: " + ciudad + ", Animales acogidos: " + obtenerNumeroDeAnimales() + "]";
    }
}
