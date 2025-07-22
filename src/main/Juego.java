package main;
import gestion.GestorPersonajes;


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
                    gPersonaje.generarPersonajeAleatorio();
                    break;
                case 2:
                    System.out.println("Vamos a crear nuestro equipo! Personalizá a tus jugadores: ");
                    gPersonaje.generarPersonajeManual();
                    break;
                case 3:
                    System.out.println("Leer logs anteriores");
                    break;
                case 4:
                    System.out.println("Borrar logs");
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