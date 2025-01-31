package org.example.DAO;

import org.example.entities.Animales;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Animales.
 * Esta interfaz establece los métodos necesarios para realizar operaciones de obtención,
 * búsqueda, inserción, actualización y eliminación de animales en la base de datos.
 *
 * @author Lucas
 */
public interface AnimalesInt {

    /**
     * Obtiene todos los animales de la base de datos.
     *
     * @return Lista con todos los registros de animales.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    List<Animales> obtenerTodos() throws HibernateException;

    /**
     * Busca animales por especie.
     *
     * @param especie Especie de los animales a buscar.
     * @return Lista de animales de la especie indicada.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    List<Animales> buscarPorEspecie(String especie) throws HibernateException;

    /**
     * Busca animales según su edad.
     *
     * @param edad Edad de los animales a buscar.
     * @return Lista de animales con la edad especificada.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    List<Animales> buscarPorEdad(int edad) throws HibernateException;

    /**
     * Busca animales cuya descripción contenga un texto específico.
     *
     * @param descripcion Texto parcial de la descripción a buscar.
     * @return Lista de animales que coincidan con el criterio de búsqueda.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    List<Animales> buscarPorDescripcion(String descripcion) throws HibernateException;

    /**
     * Inserta un nuevo animal en la base de datos.
     *
     * @param animal Objeto Animales a registrar.
     * @return El animal registrado.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    Animales guardar(Animales animal) throws HibernateException;

    /**
     * Actualiza los datos de un animal existente.
     *
     * @param animal Objeto Animales con la información actualizada.
     * @return El animal actualizado.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    Animales actualizar(Animales animal) throws HibernateException;

    /**
     * Elimina un animal de la base de datos según su ID.
     *
     * @param id Identificador del animal a eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} si no se encontró el registro.
     * @throws HibernateException En caso de error de conexión o consulta.
     */
    boolean eliminarPorId(Long id) throws HibernateException;
}
