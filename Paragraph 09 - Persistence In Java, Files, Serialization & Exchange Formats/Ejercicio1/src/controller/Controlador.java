package controller;

import model.Partido;
import model.PartidoLoader;
import model.Quiniela;
import view.Vista;
import java.util.List;

public class Controlador {
    private final Quiniela quiniela;
    private final Vista vista;

    public Controlador(Quiniela quiniela, Vista vista) {
        if (quiniela == null || vista == null) {
            throw new IllegalArgumentException("Quiniela y Vista no pueden ser null");
        }
        this.quiniela = quiniela;
        this.vista = vista;
    }

    public void iniciar() {
        try {
            cargarPartidos();
            mostrarPartidos();
        } catch (Exception e) {
            vista.mostrarError("Error al iniciar la aplicación: " + e.getMessage());
        }
    }

    private void cargarPartidos() {
        try {
            List<Partido> partidos = PartidoLoader.cargarPartidos();
            quiniela.cargarPartidos(partidos);
        } catch (Exception e) {
            vista.mostrarError("Error al cargar los partidos: " + e.getMessage());
        }
    }

    private void mostrarPartidos() {
        vista.mostrarPartidos(quiniela.getPartidos());
    }
}