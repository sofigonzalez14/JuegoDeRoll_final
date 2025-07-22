package modelo;
import java.util.Random;
public enum Nombres {
    HUMANO("Arturo", "Beatriz", "Carlos", "Diana", "Eduardo", "Fernando", "Gabriela", "Héctor", "Isabel", "Javier"),
    ORCO("Gorak", "Throk", "Mugrak", "Zugor", "Drog", "Krug", "Varg", "Borth", "Urzok", "Grash"),
    ELFO("Legolas", "Aerendil", "Thalindra", "Felarion", "Sylwen", "Elarion", "Valandil", "Naeris", "Ithil", "Caladwen");

    private final String[] nombres;
    Nombres(String... nombres){
        this.nombres = nombres;
    }
    public String getNombreAleatorio(){
        Random rand = new Random();
        return nombres[rand.nextInt(nombres.length)];
    }
    public static String obtenerNombrePorRaza(Raza raza){
        return switch (raza){
            case HUMANO -> HUMANO.getNombreAleatorio();
            case ORCO -> ORCO.getNombreAleatorio();
            case ELFO ->ELFO.getNombreAleatorio();
            default -> "Desconocido";
        };


    }
}
