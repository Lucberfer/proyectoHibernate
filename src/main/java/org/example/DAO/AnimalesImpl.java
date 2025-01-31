package org.example.DAO;

import org.example.entities.Animales;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Implementación de la interfaz AnimalesInt para gestionar operaciones CRUD con Hibernate.
 * Esta clase se encarga de la gestión de los animales en la base de datos a través de Hibernate.
 * Proporciona métodos para realizar operaciones de obtención, búsqueda, inserción, actualización y eliminación.
 *
 * @author Lucas
 */
public class AnimalesImpl implements AnimalesInt {

    private final Session session;

    /**
     * Constructor que recibe una sesión de Hibernate.
     *
     * @param session Sesión de Hibernate para operar sobre la base de datos.
     */
    public AnimalesImpl(Session session) {
        this.session = session;
    }

    /**
     * Obtiene todos los registros de animales en la base de datos.
     *
     * @return Lista de animales almacenados en la base de datos.
     */
    @Override
    public List<Animales> obtenerTodos() {
        return session.createQuery("FROM Animales", Animales.class).list();
    }

    /**
     * Busca animales por especie.
     *
     * @param especie Especie de los animales a buscar.
     * @return Lista de animales que coincidan con la especie especificada.
     */
    @Override
    public List<Animales> buscarPorEspecie(String especie) {
        return session.createQuery("FROM Animales WHERE especie = :especie", Animales.class)
                .setParameter("especie", especie)
                .list();
    }

    /**
     * Busca animales por edad.
     *
     * @param edad Edad de los animales a buscar.
     * @return Lista de animales que tengan la edad especificada.
     */
    @Override
    public List<Animales> buscarPorEdad(int edad) {
        return session.createQuery("FROM Animales WHERE edad = :edad", Animales.class)
                .setParameter("edad", edad)
                .list();
    }

    /**
     * Busca animales que contengan un texto específico en su descripción.
     *
     * @param descripcion Texto a buscar dentro de la descripción.
     * @return Lista de animales con descripciones que coincidan con el criterio proporcionado.
     */
    @Override
    public List<Animales> buscarPorDescripcion(String descripcion) {
        return session.createQuery("FROM Animales WHERE descripcion LIKE :descripcion", Animales.class)
                .setParameter("descripcion", "%" + descripcion + "%")
                .list();
    }

    /**
     * Guarda un nuevo animal en la base de datos.
     *
     * @param animal Objeto Animales a registrar en la base de datos.
     * @return El animal registrado con la información actualizada, incluyendo su ID asignado.
     * @throws HibernateException Si ocurre un error durante el proceso de guardado.
     */
    @Override
    public Animales guardar(Animales animal) {
        Transaction tx = session.beginTransaction();
        try {
            session.save(animal);
            tx.commit();
            return animal;
        } catch (HibernateException e) {
            tx.rollback();
            throw new HibernateException("Error al guardar el animal.", e);
        }
    }

    /**
     * Actualiza los datos de un animal en la base de datos.
     *
     * @param animal Objeto Animales con la información actualizada que se desea guardar.
     * @return El animal actualizado.
     * @throws HibernateException Si ocurre un error durante el proceso de actualización.
     */
    @Override
    public Animales actualizar(Animales animal) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(animal);
            tx.commit();
            return animal;
        } catch (HibernateException e) {
            tx.rollback();
            throw new HibernateException("Error al actualizar el animal.", e);
        }
    }

    /**
     * Elimina un animal de la base de datos por su ID.
     *
     * @param id Identificador del animal a eliminar.
     * @return {@code true} si el animal se eliminó correctamente, {@code false} si no se encontró el animal.
     * @throws HibernateException Si ocurre un error durante el proceso de eliminación.
     */
    @Override
    public boolean eliminarPorId(Long id) {
        Transaction tx = session.beginTransaction();
        try {
            Animales animal = session.get(Animales.class, id);
            if (animal != null) {
                session.delete(animal);
                tx.commit();
                return true;
            }
            return false;
        } catch (HibernateException e) {
            tx.rollback();
            throw new HibernateException("Error al eliminar el animal.", e);
        }
    }
}
