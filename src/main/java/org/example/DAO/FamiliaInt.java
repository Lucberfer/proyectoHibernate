package org.example.DAO;

import org.example.entities.Familia;
import java.util.List;

/**
 * Interfaz que define las operaciones básicas para la gestión de familias en la base de datos.
 * Esta interfaz proporciona métodos para obtener, registrar, modificar y eliminar registros de familias.
 *
 * @see Familia
 */
public interface FamiliaInt {

    /**
     * Obtiene el listado completo de familias registradas.
     *
     * @return Una lista con todas las familias almacenadas.
     */
    List<Familia> obtenerTodas();

    /**
     * Localiza una familia en la base de datos a través de su identificador único.
     *
     * @param id Identificador de la familia a buscar.
     * @return El objeto {@link Familia} correspondiente, o {@code null} si no se encuentra.
     */
    Familia buscarPorId(Long id);

    /**
     * Busca todas las familias que residen en una ciudad específica.
     *
     * @param ciudad Nombre de la ciudad a consultar.
     * @return Una lista de familias que tienen residencia en la ciudad proporcionada.
     */
    List<Familia> buscarPorCiudad(String ciudad);

    /**
     * Registra una nueva familia en el sistema.
     *
     * @param familia Objeto {@link Familia} que será almacenado en la base de datos.
     * @return La familia recién creada.
     */
    Familia registrar(Familia familia);

    /**
     * Modifica los datos de una familia previamente registrada.
     *
     * @param familia Objeto {@link Familia} con los nuevos valores a actualizar.
     * @return La familia después de ser actualizada.
     */
    Familia modificar(Familia familia);

    /**
     * Elimina del sistema una familia utilizando su ID.
     *
     * @param id Identificador de la familia que se desea eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} si la familia no existe.
     */
    boolean eliminarPorId(Long id);
}
