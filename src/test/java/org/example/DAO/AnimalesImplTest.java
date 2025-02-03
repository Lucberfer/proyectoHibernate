package org.example.DAO;

import org.example.entities.Animales;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalesImplTest {

    private Session session;
    private AnimalesImpl animalesDao;

    @BeforeEach
    void setUp() {
        session = HibernateUtil.getSession();
        animalesDao = new AnimalesImpl(session);
    }

    @AfterEach
    void tearDown() {
        HibernateUtil.closeSession(session);
    }

    @Test
    void testGuardar() {
        Animales animal = new Animales("León", "Felino", 5, "Rey de la selva");
        session.beginTransaction();
        session.save(animal);
        session.getTransaction().commit();

        Animales resultado = session.get(Animales.class, animal.getId());
        assertNotNull(resultado);
        assertEquals("León", resultado.getNombre());
    }

    @Test
    void testActualizar() {
        Animales animal = new Animales("Tigre", "Felino", 3, "Gran felino");
        session.beginTransaction();
        session.save(animal);
        session.getTransaction().commit();

        animal.setDetalles("Felino ágil");
        session.beginTransaction();
        session.update(animal);
        session.getTransaction().commit();

        Animales resultado = session.get(Animales.class, animal.getId());
        assertNotNull(resultado);
        assertEquals("Felino ágil", resultado.getDetalles());
    }



    @Test
    void testObtenerTodos() {
        session.beginTransaction();
        List<Animales> animales = animalesDao.obtenerTodos();
        session.getTransaction().commit();

        assertNotNull(animales);
    }

}