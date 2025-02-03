package org.example.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.example.DAO.FamiliaImpl;
import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

class FamiliaImplTest {

    private Session session;
    private FamiliaImpl familiaDao;

    @BeforeEach
    void setUp() {
        session = HibernateUtil.getSession();
        familiaDao = new FamiliaImpl(session);
    }

    @AfterEach
    void tearDown() {
        HibernateUtil.closeSession(session);
    }

    @Test
    void testRegistrar() {
        Familia familia = new Familia("Gómez", 45, "Madrid");
        session.beginTransaction();
        session.save(familia);
        session.getTransaction().commit();

        Familia resultado = session.get(Familia.class, familia.obtenerId());
        assertNotNull(resultado);
        assertEquals("Gómez", resultado.obtenerNombre());
    }

    @Test
    void testModificar() {
        Familia familia = new Familia("Fernández", 50, "Barcelona");
        session.beginTransaction();
        session.save(familia);
        session.getTransaction().commit();

        familia.asignarCiudad("Valencia");
        session.beginTransaction();
        session.update(familia);
        session.getTransaction().commit();

        Familia resultado = session.get(Familia.class, familia.obtenerId());
        assertNotNull(resultado);
        assertEquals("Valencia", resultado.obtenerCiudad());
    }

    @Test
    void testEliminarPorId() {
        Familia familia = new Familia("López", 38, "Sevilla");
        session.beginTransaction();
        session.save(familia);
        session.getTransaction().commit();

        session.beginTransaction();
        boolean resultado = familiaDao.eliminarPorId(familia.obtenerId());
        session.getTransaction().commit();

        assertTrue(resultado);
        assertNull(session.get(Familia.class, familia.obtenerId()));
    }

    @Test
    void testObtenerTodas() {
        session.beginTransaction();
        List<Familia> familias = familiaDao.obtenerTodas();
        session.getTransaction().commit();

        assertNotNull(familias);
    }

}