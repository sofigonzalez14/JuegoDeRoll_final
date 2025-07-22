package modelo;
public class Personaje {
    private String nombre;
    private String raza;
    private String apodo;
    private int edad;
    private int nivel;
    private Estadisticas estadisticas;
    
    public Personaje(String nombre, String apodo, String raza, int edad, int nivel, Estadisticas estadisticas) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.raza = raza;
        this.edad = edad;
        this.nivel = nivel;
        this.estadisticas = estadisticas;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public String getApodo() {
        return apodo;
    }

    public int getEdad() {
        return edad;
    }

    public int getNivel() {
        return nivel;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    
    @Override
    public String toString() {
    return "Personaje {" +
           "nombre =' " + nombre + '\'' +
           ", apodo =' " + apodo + '\'' +
           ", raza =' " + raza + '\'' +
           ", edad = " + edad +
           ", nivel = " + nivel +
           ", estadisticas = " + estadisticas +
           '}';
}
}
