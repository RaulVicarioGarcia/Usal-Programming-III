package view;

import model.Partido;
import java.util.List;

public class Vista {
    public void mostrarPartidos(List<Partido> partidos) {
        if (partidos == null || partidos.isEmpty()) {
            System.out.println("No hay partidos para mostrar.");
            return;
        }

        System.out.println("\n=== Partidos de la jornada ===");
        for (int i = 0; i < partidos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, partidos.get(i));
        }
        System.out.println("===========================\n");
    }

    public void mostrarMensaje(String mensaje) {
        if (mensaje != null && !mensaje.trim().isEmpty()) {
            System.out.println(mensaje);
        }
    }

    public void mostrarError(String error) {
        System.err.println("ERROR: " + error);
    }
}