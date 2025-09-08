package gestion;

import java.util.*;
import modelo.*;

public class GestorPersonajes {
    private List<Personaje> personajes1;
    private List<Personaje> personajes2;
    private Random random;
    private Set<String> nombresUsados;
    private Set<String> apodosUsados;

    public GestorPersonajes() {
        this.personajes1 = new ArrayList<>();
        this.personajes2 = new ArrayList<>();
        this.random = new Random();
        this.nombresUsados = new HashSet<>();
        this.apodosUsados = new HashSet<>();
    }

    public List<Personaje> getPersonajes1() {
        return personajes1;
    }
    public List<Personaje> getPersonajes2() {
        return personajes2;
    }

    public void limpiarEquipos() {
        personajes1.clear();
        personajes2.clear();
    }
    public void generarPersonajeAleatorio() {
        System.out.println("Generando 6 personajes aleatorios...");

        for (int i = 0; i < 6; i++) {
            Personaje p = generarPersonajeAleatorio("Jugador " + (i < 3 ? "1" : "2"));
            if (i < 3) {
                personajes1.add(p);
            } else {
                personajes2.add(p);
            }
        }

        System.out.println("Personajes generados para el Jugador 1:");
        for (Personaje p : personajes1) {
            System.out.println(p.toString());
        }

        System.out.println("Personajes generados para el Jugador 2:");
        for (Personaje p : personajes2) {
            System.out.println(p.toString());
        }

        System.out.println("Personajes generados exitosamente!!!");
    }

    // Genera un personaje con atributos aleatorios
    private Personaje generarPersonajeAleatorio(String jugador) {
        Raza raza = Raza.values()[random.nextInt(Raza.values().length)];
        String nombre = obtenerNombreAleatorio(raza);
        String apodo = obtenerApodoAleatorio(raza);
        int edad = random.nextInt(301);
        int nivel = random.nextInt(10) + 1;

        Estadisticas estadisticas = new Estadisticas(
                100,
                random.nextInt(10) + 1,
                random.nextInt(5) + 1,
                random.nextInt(10) + 1,
                random.nextInt(10) + 1);

        Personaje personaje = new Personaje(nombre, apodo, raza.name(), edad, nivel, estadisticas);

        return personaje;
    }

    private String obtenerNombreAleatorio(Raza raza) {
        String nombre;
        do {
            switch (raza) {
                case HUMANO:
                    nombre = Nombres.HUMANO.getNombreAleatorio();
                    break;
                case ORCO:
                    nombre = Nombres.ORCO.getNombreAleatorio();
                    break;
                case ELFO:
                    nombre = Nombres.ELFO.getNombreAleatorio();
                    break;
                default:
                    nombre = "Desconocido";
            }
        } while (nombresUsados.contains(nombre));
        nombresUsados.add(nombre);
        return nombre;
    }

    private String obtenerApodoAleatorio(Raza raza) {
        String apodo;
        do {
            switch (raza) {
                case HUMANO:
                    apodo = Apodos.HUMANO.getApodoAleatorio();
                    break;
                case ORCO:
                    apodo = Apodos.ORCO.getApodoAleatorio();
                    break;
                case ELFO:
                    apodo = Apodos.ELFO.getApodoAleatorio();
                    break;
                default:
                    apodo = "Sin Apodo";
            }
        } while (apodosUsados.contains(apodo));
        apodosUsados.add(apodo);
        return apodo;
    }

    public void generarPersonajeManual() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {

            System.out.println("Ingresá el juegador n°" + i);

            // Solicitar el nombre
            System.out.print("Ingrese el nombre del personaje: ");
            String nombre = scanner.nextLine();

            // Solicitar el apodo
            System.out.print("Ingrese el apodo del personaje: ");
            String apodo = scanner.nextLine();

            // Solicitar la raza
            System.out.print("Seleccione la raza (HUMANO, ORCO, ELFO): ");
            String razaInput = scanner.nextLine().toUpperCase();
            Raza raza;
            try {
                raza = Raza.valueOf(razaInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Raza no válida. Se asignará 'HUMANO' por defecto.");
                raza = Raza.HUMANO; // Asignamos un valor por defecto
            }

            // Solicitar edad y verificamos que sea un valor válido
            int edadInput;
            while (true) {
                System.out.print("Ingrese la edad del personaje (0-300): ");
                try {
                    edadInput = Integer.parseInt(scanner.nextLine());
                    if (edadInput >= 0 && edadInput <= 300) {
                        break;
                    } else {
                        System.out.println("La edad debe estar entre 0 y 300.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor ingrese una edad válida.");
                }
            }

            // Solicitar el nivel
            int nivel;
            while (true) {
                System.out.print("Ingrese el nivel del personaje (1-10): ");
                try {
                    nivel = Integer.parseInt(scanner.nextLine());
                    if (nivel > 0 && nivel <= 10) {
                        break;
                    } else {
                        System.out.println("El nivel debe ser entre 0 y 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un nivel valido.");
                }
            }

            // Crear estadísticas aleatorias
            Estadisticas estadisticas = new Estadisticas(
                    100, // Salud
                    (int) (Math.random() * 10) + 1, 
                    (int) (Math.random() * 5) + 1, 
                    (int) (Math.random() * 10) + 1, 
                    (int) (Math.random() * 10) + 1 
            );

            // Crear el personaje manual
            Personaje personaje = new Personaje(nombre, apodo, raza.name(), edadInput, nivel, estadisticas);

            System.out.println("Personaje creado exitosamente: " + personaje.toString());

            personajes1.add(personaje);

        }
        // Generamos 3 personajes aleatorios para la batalla
        System.out.println("Creando personajes aleatorios para el jugador 2...");
        for (int i = 0; i < 3; i++) {
            Personaje p = generarPersonajeAleatorio("2");
            personajes2.add(p);
        }
        System.out.println("Equipo del Jugador 1:");
        for (Personaje p : personajes1) {
            System.out.println(p);
        }

        System.out.println("Equipo del Jugador 2:");
        for (Personaje p : personajes2) {
            System.out.println(p);
        }

        System.out.println("Ambos equipos estan listos para la batalla! Que comience el juego...");
    }
}