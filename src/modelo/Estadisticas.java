package modelo;
public class Estadisticas {
    private int salud;  
    private int fuerza; 
    private int destreza; 
    private int armadura; 
    private int velocidad;

    
    public Estadisticas(int salud, int fuerza, int destreza, int armadura, int velocidad) {
        this.salud = salud; 
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.armadura = armadura;
        this.velocidad = velocidad;
    }

    // Getters
    public int getSalud() { 
        return salud; }
    public int getFuerza() { 
        return fuerza; }
    public int getDestreza() { 
        return destreza; }
    public int getArmadura() { 
        return armadura; }
    public int getVelocidad() { 
        return velocidad; }

    // Setters (con parámetros)
    public void setSalud(int salud) { 
        this.salud = salud; }
    public void setFuerza(int fuerza) { 
        this.fuerza = fuerza; }
    public void setDestreza(int destreza) { 
        this.destreza = destreza; }
    public void setArmadura(int armadura) { 
        this.armadura = armadura; }
    public void setVelocidad(int velocidad) { 
        this.velocidad = velocidad; }
        
     @Override
    public String toString() {
        return "Salud: " + salud + "\n" +
               "Velocidad: " + velocidad + "\n" +
               "Destreza: " + destreza + "\n" +
               "Fuerza: " + fuerza + "\n" +
               "Armadura: " + armadura;
    }
  
}
