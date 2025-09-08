package main;
import gestion.*;


public class Juego{
    private Menu menu;
    private GestorPersonajes gPersonaje;

    public Juego(){
        this.menu = new Menu();
        this.gPersonaje = new GestorPersonajes();

    }

    public void iniciar(){
        boolean jugando = true;
        while (jugando){
            int opcion = menu.MenuYOpciones();
            switch (opcion) {
                case 1:
                    System.out.println("Creando personajes aleatorios: ");
                    gPersonaje.limpiarEquipos();
                    gPersonaje.generarPersonajeAleatorio();
                    boolean verbose1 = menu.elegirModoVerbose();
                    new Combate(gPersonaje.getPersonajes1(), gPersonaje.getPersonajes2(), verbose1).iniciarCombate();
                    break;
                case 2:
                    System.out.println("Vamos a crear nuestro equipo! Personalizá a tus jugadores: ");
                    gPersonaje.limpiarEquipos();
                    gPersonaje.generarPersonajeManual();
                    boolean verbose2 = menu.elegirModoVerbose();
                    new Combate(gPersonaje.getPersonajes1(), gPersonaje.getPersonajes2(), verbose2).iniciarCombate();
                    break;
                case 3:
                    System.out.println("Leer logs anteriores");
                    gestion.GestorLogs.mostrarLogs();
                    break;
                case 4:
                    System.out.println("Borrar logs");
                    gestion.GestorLogs.borrarLogs();
                    break;
                case 5:
                    System.out.println("Saliendo del juego.");
                    jugando = false;
                    break;
                default: 
                    System.out.println("Opcion no valida, intente nuevamente.");

            }
        }
    }
}