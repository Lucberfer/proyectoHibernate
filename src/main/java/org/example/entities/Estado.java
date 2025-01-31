package org.example.entities;

/**
 * Enumeración que representa los diferentes estados en los que un animal puede encontrarse en el sistema.
 * Cada estado está asociado con una descripción detallada que indica la situación o el proceso en el que
 * se encuentra el animal. Los estados posibles son: recién abandonado, en refugio, y próximamente en acogida.
 */
public enum Estado {

    /** Estado que indica que el animal ha sido recién abandonado. */
    RECIEN_ABANDONADO("Recién abandonado"),

    /** Estado que indica que el animal se encuentra en un refugio. */
    EN_REFUGIO("En refugio"),

    /** Estado que indica que el animal estará próximamente en acogida. */
    PROXIMAMENTE_EN_ACOGIDA("Próximamente en acogida");

    private final String descripcion;

    /**
     * Constructor privado para establecer la descripción asociada al estado.
     *
     * @param descripcion Descripción del estado.
     */
    private Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo que devuelve la descripción asociada al estado.
     *
     * @return La descripción del estado.
     */
    public String obtenerDescripcion() {
        return descripcion;
    }

    /**
     * Metodo estático para obtener un estado a partir de su descripción.
     *
     * @param descripcion Descripción del estado a buscar.
     * @return El estado correspondiente a la descripción proporcionada.
     * @throws IllegalArgumentException Si la descripción no coincide con ningún estado válido.
     */
    public static Estado obtenerEstadoPorDescripcion(String descripcion) {
        for (Estado estado : Estado.values()) {
            if (estado.obtenerDescripcion().equalsIgnoreCase(descripcion)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Descripción no válida. Valores válidos: " + obtenerDescripcionesValidas());
    }

    /**
     * Metodo que devuelve todas las descripciones de los estados disponibles en el sistema.
     *
     * @return Una cadena con todas las descripciones de los estados válidos.
     */
    private static String obtenerDescripcionesValidas() {
        StringBuilder descripciones = new StringBuilder();
        for (Estado estado : Estado.values()) {
            descripciones.append(estado.obtenerDescripcion()).append(", ");
        }
        // Elimina la última coma
        return descripciones.substring(0, descripciones.length() - 2);
    }

    /**
     * Sobrescribe el metodo {@link Object#toString()} para mostrar la descripción del estado.
     *
     * @return La descripción del estado.
     */
    @Override
    public String toString() {
        return obtenerDescripcion();
    }
}
