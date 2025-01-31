package org.example.DAO;

import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Clase que proporciona operaciones CRUD y consultas específicas para la entidad {@link Familia}.
 * Implementa la interfaz {@link FamiliaInt}.
 */
public class FamiliaImpl implements FamiliaInt {

    private final Session session;

    /**
     * Constructor que recibe una sesión de Hibernate.
     *
     * @param session Instancia de sesión activa para realizar operaciones en la base de datos.
     */
    public FamiliaImpl(Session session) {
        this.session = session;
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Familia.class)
                .buildSessionFactory();
    }

    /**
     * Recupera todas las familias almacenadas en la base de datos.
     *
     * @return Lista con todas las familias registradas.
     * @throws HibernateException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Familia> obtenerTodas() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Familia", Familia.class).getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("No se pudieron obtener las familias", e);
        }
    }

    /**
     * Busca una familia por su identificador único.
     *
     * @param id ID de la familia a buscar.
     * @return Objeto {@link Familia} si se encuentra, de lo contrario, {@code null}.
     * @throws HibernateException Si ocurre un error en la consulta.
     */
    @Override
    public Familia buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Familia.class, id);
        } catch (HibernateException e) {
            throw new HibernateException("No se pudo encontrar la familia con ID: " + id, e);
        }
    }

    /**
     * Obtiene todas las familias que residen en la ciudad especificada.
     *
     * @param ciudad Nombre de la ciudad a filtrar.
     * @return Lista de familias encontradas en la ciudad indicada.
     * @throws HibernateException Si ocurre un error en la consulta.
     */
    @Override
    public List<Familia> buscarPorCiudad(String ciudad) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Familia WHERE ciudad = :ciudad", Familia.class)
                    .setParameter("ciudad", ciudad)
                    .getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Error al obtener familias de la ciudad: " + ciudad, e);
        }
    }

    /**
     * Registra una nueva familia en la base de datos.
     *
     * @param familia Objeto {@link Familia} a insertar.
     * @return La familia registrada.
     * @throws HibernateException Si ocurre un error durante la inserción.
     */
    @Override
    public Familia registrar(Familia familia) {
        Transaction transaccion = null;
        try (Session session = HibernateUtil.getSession()) {
            transaccion = session.beginTransaction();
            session.persist(familia);
            transaccion.commit();
            return familia;
        } catch (HibernateException e) {
            if (transaccion != null) transaccion.rollback();
            throw new HibernateException("No se pudo registrar la familia", e);
        }
    }

    /**
     * Modifica la información de una familia existente.
     *
     * @param familia Objeto {@link Familia} con los datos actualizados.
     * @return La familia después de la actualización.
     * @throws HibernateException Si ocurre un error durante la actualización.
     */
    @Override
    public Familia modificar(Familia familia) {
        Transaction transaccion = null;
        try (Session session = HibernateUtil.getSession()) {
            transaccion = session.beginTransaction();
            session.merge(familia);
            transaccion.commit();
            return familia;
        } catch (HibernateException e) {
            if (transaccion != null) transaccion.rollback();
            throw new HibernateException("No se pudo actualizar la información de la familia", e);
        }
    }

    /**
     * Elimina una familia de la base de datos según su ID.
     *
     * @param id Identificador de la familia a eliminar.
     * @return {@code true} si la familia fue eliminada, {@code false} si no se encontró el registro.
     * @throws HibernateException Si ocurre un error durante la eliminación.
     */
    @Override
    public boolean eliminarPorId(Long id) {
        Transaction transaccion = null;
        try (Session session = HibernateUtil.getSession()) {
            transaccion = session.beginTransaction();
            Familia familia = session.get(Familia.class, id);
            if (familia != null) {
                session.remove(familia);
                transaccion.commit();
                return true;
            }
            transaccion.rollback();
            return false;
        } catch (HibernateException e) {
            if (transaccion != null) transaccion.rollback();
            throw new HibernateException("No se pudo eliminar la familia con ID: " + id, e);
        }
    }
}
