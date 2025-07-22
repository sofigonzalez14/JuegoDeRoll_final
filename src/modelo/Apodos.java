package modelo;
import java.util.Random;

public enum Apodos {
    HUMANO("El Valiente", "El Sabio", "El Justiciero", "La Sombra", "El Guerrero", "El Noble", "El Defensor", "El Intrépido", "El Estratega", "El Protector"),
    ORCO("Rompecráneos", "El Destructor", "Garra Sangrienta", "Colmillo", "El Implacable", "Hachamortal", "El Furioso", "Sangre Fría", "El Aniquilador", "El Temible"),
    ELFO("Flecha Veloz", "El Susurro", "Ojo de Halcón", "Cantor del Bosque", "Hoja Silenciosa", "El Iluminado", "El Hechicero", "Voz del Viento", "El Guardián", "Brisa Ligera");

    private final String[] apodos;

    Apodos(String... apodos) {
        this.apodos = apodos;
    }
    
    public String getApodoAleatorio(){
        Random rand = new Random();
        return apodos[rand.nextInt(apodos.length)];
    }
}

