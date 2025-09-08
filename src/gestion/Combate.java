package gestion;

import java.util.List;
import java.util.Random;

import modelo.Estadisticas;
import modelo.Personaje;
import modelo.Raza;

public class Combate {

    private final List<Personaje> equipo1;
    private final List<Personaje> equipo2;
    private final Random random = new Random();

    private int nroRonda = 1;
    private boolean turnoJugador1;
    private Boolean perdioJugador1RondaAnterior;
    private final boolean verbose;

    public Combate(List<Personaje> equipo1, List<Personaje> equipo2) {
        this(equipo1, equipo2, false);
    }

    public Combate(List<Personaje> equipo1, List<Personaje> equipo2, boolean verbose) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.verbose = verbose;
        this.turnoJugador1 = random.nextBoolean();
        this.perdioJugador1RondaAnterior = null;
    }

    public void iniciarCombate() {
        GestorLogs.iniciarPartida();

        log("===========================================");
        log(" MODO DE COMBATE: " + (verbose ? "DETALLADO" : "RESUMIDO"));
        log("===========================================\n");

        log(">>> COMIENZA EL COMBATE <<<");
        mostrarEquipo("Equipo Jugador 1:", equipo1);
        mostrarEquipo("Equipo Jugador 2:", equipo2);
        log("Inicia atacando: " + (turnoJugador1 ? "Jugador 1" : "Jugador 2"));

        while (!equipo1.isEmpty() && !equipo2.isEmpty()) {
            log("\n===== RONDA " + nroRonda + " =====");

            Personaje p1 = equipo1.get(random.nextInt(equipo1.size()));
            Personaje p2 = equipo2.get(random.nextInt(equipo2.size()));

            log("Enfrentamiento: " + resumenCorto(p1) + "  VS  " + resumenCorto(p2));

            if (perdioJugador1RondaAnterior != null) {
                turnoJugador1 = perdioJugador1RondaAnterior;
            }

            boolean murioAlguien = false;
            int ataquesJ1 = 0, ataquesJ2 = 0;

            while (ataquesJ1 < 7 || ataquesJ2 < 7) {
                if (turnoJugador1 && ataquesJ1 < 7) {
                    murioAlguien = ataque(p1, p2, 1);
                    ataquesJ1++;
                    if (murioAlguien) break;
                } else if (!turnoJugador1 && ataquesJ2 < 7) {
                    murioAlguien = ataque(p2, p1, 2);
                    ataquesJ2++;
                    if (murioAlguien) break;
                }
                turnoJugador1 = !turnoJugador1;
                if (ataquesJ1 >= 7 && ataquesJ2 >= 7) break;
            }

            boolean murioP1 = p1.getEstadisticas().getSalud() <= 0;
            boolean murioP2 = p2.getEstadisticas().getSalud() <= 0;

            if (murioP1 && !murioP2) {
                log(">> Muere " + p1.getNombre() + " (Jugador 1).");
                equipo1.remove(p1);
                aplicarMejora(p2);
                perdioJugador1RondaAnterior = true;
            } else if (!murioP1 && murioP2) {
                log(">> Muere " + p2.getNombre() + " (Jugador 2).");
                equipo2.remove(p2);
                aplicarMejora(p1);
                perdioJugador1RondaAnterior = false;
            } else {
                log(">> Nadie murió en la ronda " + nroRonda + ". Ambos mantienen sus cartas.");
            }

            log((verbose ? "[DET]" : "[RES]") + " --- Resumen Ronda " + nroRonda + " ---");
            log(String.format("Cartas vivas: J1=%d | J2=%d", equipo1.size(), equipo2.size()));
            if (murioP1 ^ murioP2) {
                log("Próxima ronda inicia: " + (perdioJugador1RondaAnterior ? "Jugador 1" : "Jugador 2"));
            } else {
                log("Próxima ronda: inicia el mismo que esta ronda.");
            }
            log("------------------------------------------");

            nroRonda++;
        }

        String resultado;
        if (equipo1.isEmpty() && equipo2.isEmpty()) {
            resultado = "Empate (ambos sin cartas).";
        } else if (equipo2.isEmpty()) {
            resultado = "¡Gana Jugador 1!";
        } else {
            resultado = "¡Gana Jugador 2!";
        }
        log("\n>>> " + resultado + " <<<");
        GestorLogs.finalizarPartida(resultado);
    }

    private boolean ataque(Personaje atacante, Personaje defensor, int nroJugadorQueAtaca) {
        Estadisticas ea = atacante.getEstadisticas();
        Estadisticas ed = defensor.getEstadisticas();

        int PD = ea.getDestreza() * ea.getFuerza() * Math.max(1, atacante.getNivel());
        int ED = random.nextInt(100) + 1;
        long VA = (long) PD * ED;
        int PDEF = ed.getArmadura() * ed.getVelocidad();

        double danioBase = ((VA - PDEF) / 500.0) * 100.0;
        double multRaza = multiplicadorPorRaza(atacante.getRaza());
        double danio = danioBase * multRaza;
        if (danio < 0) danio = 0;

        int danioFinal = (int) Math.round(danio);
        int saludDespues = Math.max(0, ed.getSalud() - danioFinal);
        ed.setSalud(saludDespues);

        if (verbose) {
            log(String.format(
                    "[DET] J%d: %-12s -> %-12s | PD=%-4d | ED=%-3d%% | VA=%-6d | PDEF=%-3d | Daño=%-4d | HP %-12s=%d",
                    nroJugadorQueAtaca, atacante.getNombre(), defensor.getNombre(),
                    PD, ED, VA, PDEF, danioFinal, defensor.getNombre(), saludDespues
            ));
        } else {
            log(String.format("[RES] %-12s golpea a %-12s por %-4d | HP %-12s=%d",
                    atacante.getNombre(), defensor.getNombre(),
                    danioFinal, defensor.getNombre(), saludDespues
            ));
        }

        return saludDespues <= 0;
    }

    private double multiplicadorPorRaza(String razaStr) {
        Raza raza;
        try { raza = Raza.valueOf(razaStr.toUpperCase()); }
        catch (Exception e) { raza = Raza.HUMANO; }
        return switch (raza) {
            case ELFO -> 1.05;
            case ORCO -> 1.10;
            case HUMANO -> 1.00;
        };
    }

    private void aplicarMejora(Personaje ganador) {
        if (random.nextBoolean()) {
            int nueva = Math.min(100, ganador.getEstadisticas().getSalud() + 10);
            ganador.getEstadisticas().setSalud(nueva);
            log("** Premio: " + ganador.getNombre() + " recupera +10 de salud (queda en " + nueva + ").");
        } else {
            ganador.setNivel(Math.min(10, ganador.getNivel() + 1));
            log("** Premio: " + ganador.getNombre() + " sube +1 nivel (queda en " + ganador.getNivel() + ").");
        }
    }

    private void mostrarEquipo(String titulo, List<Personaje> eq) {
        log(titulo);
        for (Personaje p : eq) {
            var e = p.getEstadisticas();
            log(String.format("- %s (%s) | HP:%d F:%d D:%d A:%d V:%d N:%d",
                    p.getNombre(), p.getRaza(),
                    e.getSalud(), e.getFuerza(), e.getDestreza(),
                    e.getArmadura(), e.getVelocidad(),
                    p.getNivel()));
        }
    }

    private String resumenCorto(Personaje p) {
        Estadisticas e = p.getEstadisticas();
        return p.getNombre() + " [" + p.getRaza() + "] HP=" + e.getSalud() +
                " F=" + e.getFuerza() + " D=" + e.getDestreza() +
                " A=" + e.getArmadura() + " V=" + e.getVelocidad() +
                " N=" + p.getNivel();
    }

    private void log(String s) {
        System.out.println(s);
        GestorLogs.log(s);
    }
}
