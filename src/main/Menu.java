package main;
import java.util.Scanner;

public class Menu {
 private Scanner scanner;

 public Menu (){
    this.scanner = new Scanner (System.in);
 }

 public int MenuYOpciones (){
    System.out.println("--- Menu principal ---"); 
    System.out.println("0. Instrucciones del juego");
    System.out.println("1. Iniciar una partida con personajes aleatorios");
    System.out.println("2. Iniciar una partida con personajes ingresados manualmente");
    System.out.println("3. Leer logs de partidas anteriores");
    System.out.println("4. Borrar logs");
    System.out.println("5. SALIR ");

    System.out.println("Seleccione una opcion: ");
    return scanner.nextInt();
    
 }
}