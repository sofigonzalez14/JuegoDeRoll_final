package gestion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorLogs {

    private static final Path LOG_DIR  = Paths.get("logs");
    private static final Path LOG_FILE = LOG_DIR.resolve("partidas.log");
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void iniciarPartida() {
        ensureDir();
        String header = "\n\n================= PARTIDA " + now() + " =================\n";
        append(header);
    }

    public static synchronized void log(String line) {
        ensureDir();
        append(line + System.lineSeparator());
    }

    public static synchronized void finalizarPartida(String resultado) {
        append("RESULTADO: " + resultado + "\n===============================================\n");
    }

    public static synchronized void mostrarLogs() {
        if (Files.notExists(LOG_FILE)) {
            System.out.println("No hay logs aún.");
            return;
        }
        System.out.println("====== LOGS DE PARTIDAS ======");
        try {
            Files.lines(LOG_FILE, StandardCharsets.UTF_8).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error leyendo logs: " + e.getMessage());
        }
    }

    public static synchronized void borrarLogs() {
        try {
            if (Files.exists(LOG_FILE)) {
                Files.delete(LOG_FILE);
                System.out.println("Archivo de logs borrado.");
            } else {
                System.out.println("No hay archivo de logs para borrar.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo borrar el archivo de logs: " + e.getMessage());
        }
    }

    private static void ensureDir() {
        try { Files.createDirectories(LOG_DIR);
        } catch (IOException ignored) {

        }
    }

    private static String now() {
        return LocalDateTime.now().format(TS);
    }

    private static void append(String text) {
        try {
            Files.write(LOG_FILE, text.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("No se pudo escribir en logs: " + e.getMessage());
        }
    }
}

