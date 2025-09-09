package main;


public class Instrucciones {

    // Colores ANSI
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void mostrarInstrucciones() {
        System.out.println(CYAN +
                "=====================================\n" +
                "   JUEGO DE CARTAS - INSTRUCCIONES   \n" +
                "=====================================" + RESET);

        System.out.println(YELLOW + "\nOBJETIVO" + RESET);
        System.out.println("Crear personajes (pueden ser todos aleatorios, o crear tu equipo de forma manual), enfrentarte en combates por turnos y subir de nivel.");

        System.out.println(YELLOW + "\nMECÁNICA DEL COMBATE" + RESET);
        System.out.println("- Los personajes se enfrentan por turnos.");
        System.out.println("- Atributos importantes:");
        System.out.println("   F = Fuerza --> Daño base.");
        System.out.println("   D = Defensa -->  Reduce daño.");
        System.out.println("   A = Agilidad -->  Evasión.");
        System.out.println("   V = Velocidad -->  Quién ataca primero.");
        System.out.println("   N = Nivel -->  Experiencia.");
        System.out.println("- El combate termina cuando la HP de un personaje llega a 0.");

        System.out.println(YELLOW + "\nLOGS" + RESET);
        System.out.println("- Cada combate queda registrado en un archivo.");
        System.out.println("- Podes leerlos o borrarlos desde el menú.");

        System.out.println(YELLOW + "\nFIN DE PARTIDA" + RESET);
        System.out.println("- Gana el personaje que sobreviva.");
        System.out.println("- El ganador sube de nivel.");
        System.out.println("- Podes iniciar una nueva partida y seguir jugando.");

        System.out.println(PURPLE + "\nConsejo: proba distintas combinaciones de personajes y atributos.\n" + RESET);
    }
}

