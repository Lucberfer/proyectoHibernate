package org.example;

import org.example.DAO.AnimalesImpl;
import org.example.DAO.FamiliaImpl;
import org.example.entities.Animales;
import org.example.entities.Familia;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que ejecuta el programa de gestión de un refugio de animales.
 * Permite registrar animales, buscar animales por diversas características y
 * registrar familias que acojan a los animales. Utiliza Hibernate para gestionar
 * la persistencia de los datos.
 */
public class Main {

    public static void main(String[] args) {
        // Crear la sesión de Hibernate
        Session session = HibernateUtil.getSession();

        // Crear las implementaciones de los DAOs
        AnimalesImpl animalesDAO = new AnimalesImpl(session);
        FamiliaImpl familiaDAO = new FamiliaImpl(session);

        // Crear un scanner para interactuar con el usuario
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        int opcion;
        do {
            System.out.println("\n=== Menú Refugio de Animales ===");
            System.out.println("1. Registrar nuevo animal");
            System.out.println("2. Buscar animales por especie");
            System.out.println("3. Buscar animales por edad");
            System.out.println("4. Buscar animales por descripción");
            System.out.println("5. Registrar familia que acoge un animal");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarNuevoAnimal(scanner, animalesDAO);
                    break;
                case 2:
                    buscarAnimalesPorEspecie(scanner, animalesDAO);
                    break;
                case 3:
                    buscarAnimalesPorEdad(scanner, animalesDAO);
                    break;
                case 4:
                    buscarAnimalesPorDescripcion(scanner, animalesDAO);
                    break;
                case 5:
                    registrarFamilia(scanner, animalesDAO, familiaDAO);
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }

    /**
     * Registra un nuevo animal en el sistema. Solicita los datos del animal al usuario
     * y lo guarda en la base de datos utilizando el DAO {@link AnimalesImpl}.
     *
     * @param scanner El escáner utilizado para leer la entrada del usuario.
     * @param animalesDAO El DAO utilizado para persistir los animales.
     */
    private static void registrarNuevoAnimal(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.println("Ingrese los datos del nuevo animal:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Especie (Perro, Gato, Pajarito, Cerdo_vietnamita, serpiente, camaleon, araña): ");
        String especie = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Descripción (recién abandonado, en refugio, próximamente en acogida): ");
        String descripcion = scanner.nextLine();

        // Crear el nuevo animal
        Animales nuevoAnimal = new Animales(nombre, especie, edad, descripcion);
        animalesDAO.guardar(nuevoAnimal);  // Registrar el animal
        System.out.println("Animal registrado correctamente.");
    }

    /**
     * Busca los animales por especie en el sistema. El usuario ingresa la especie
     * y el programa muestra los animales que coinciden con esa especie.
     *
     * @param scanner El escáner utilizado para leer la entrada del usuario.
     * @param animalesDAO El DAO utilizado para buscar los animales.
     */
    private static void buscarAnimalesPorEspecie(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la especie que desea buscar (Perro, Gato, Pajarito, Cerdo_vietnamita, serpiente, camaleon, araña): ");
        String especieBusqueda = scanner.nextLine();
        List<Animales> animales = animalesDAO.buscarPorEspecie(especieBusqueda);  // Buscar animales por especie
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa especie.");
        } else {
            animales.forEach(System.out::println);  // Mostrar los animales encontrados
        }
    }

    /**
     * Busca los animales por edad en el sistema. El usuario ingresa la edad y el
     * programa muestra los animales que coinciden con esa edad.
     *
     * @param scanner El escáner utilizado para leer la entrada del usuario.
     * @param animalesDAO El DAO utilizado para buscar los animales.
     */
    private static void buscarAnimalesPorEdad(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la edad de los animales a buscar: ");
        int edadBusqueda = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        List<Animales> animales = animalesDAO.buscarPorEdad(edadBusqueda);  // Buscar animales por edad
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa edad.");
        } else {
            animales.forEach(System.out::println);  // Mostrar los animales encontrados
        }
    }

    /**
     * Busca los animales por descripción en el sistema. El usuario ingresa una descripción
     * y el programa muestra los animales que coinciden con esa descripción.
     *
     * @param scanner El escáner utilizado para leer la entrada del usuario.
     * @param animalesDAO El DAO utilizado para buscar los animales.
     */
    private static void buscarAnimalesPorDescripcion(Scanner scanner, AnimalesImpl animalesDAO) {
        System.out.print("Ingrese la descripción que desea buscar (recién abandonado, en refugio, próximamente en acogida): ");
        String descripcionBusqueda = scanner.nextLine();
        List<Animales> animales = animalesDAO.buscarPorDescripcion(descripcionBusqueda);  // Buscar animales por descripción
        if (animales.isEmpty()) {
            System.out.println("No se encontraron animales con esa descripción.");
        } else {
            animales.forEach(System.out::println);  // Mostrar los animales encontrados
        }
    }

    /**
     * Registra una nueva familia que acoge a un animal. El usuario selecciona un animal
     * de la lista de animales disponibles y luego ingresa los datos de la familia.
     * El animal seleccionado es actualizado con la familia correspondiente.
     *
     * @param scanner El escáner utilizado para leer la entrada del usuario.
     * @param animalesDAO El DAO utilizado para obtener y actualizar los animales.
     * @param familiaDAO El DAO utilizado para registrar las familias.
     */
    private static void registrarFamilia(Scanner scanner, AnimalesImpl animalesDAO, FamiliaImpl familiaDAO) {
        System.out.println("Animales disponibles para acoger:");
        List<Animales> animales = animalesDAO.obtenerTodos();  // Obtener todos los animales disponibles
        if (animales.isEmpty()) {
            System.out.println("No hay animales disponibles en el refugio.");
            return;
        }

        animales.forEach(System.out::println);

        System.out.print("Ingrese la especie del animal que desea acoger: ");
        String especie = scanner.nextLine();

        // Filtrar el animal por especie
        Animales animalAcojer = animales.stream()
                .filter(a -> a.getEspecie().equalsIgnoreCase(especie))
                .findFirst()
                .orElse(null);

        if (animalAcojer == null) {
            System.out.println("No se encontró un animal con esa especie.");
        } else {
            System.out.println("Ingrese los datos de la familia:");
            System.out.print("Nombre de la familia: ");
            String nombre = scanner.nextLine();

            System.out.print("Edad de la familia: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.print("Ciudad de la familia: ");
            String ciudad = scanner.nextLine();

            Familia nuevaFamilia = new Familia(nombre, edad, ciudad);
            familiaDAO.registrar(nuevaFamilia);  // Registrar la familia

            animalAcojer.setFamilia(nuevaFamilia);  // Asignar la familia al animal
            animalesDAO.actualizar(animalAcojer);  // Actualizar el animal en la base de datos
            System.out.println("La familia ha acogido al animal correctamente.");
        }
    }
}
