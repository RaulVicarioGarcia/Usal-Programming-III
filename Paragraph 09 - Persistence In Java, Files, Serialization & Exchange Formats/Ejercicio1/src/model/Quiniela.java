package model;

import java.util.ArrayList;
import java.util.List;

public class Quiniela {

    private List<Partido> partidos;

    public Quiniela() {
        partidos = new ArrayList<>();
    }

    public void addPartido(Partido partido) {
        partidos.add(partido);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void cargarPartidos(List<Partido> nuevosPartidos) {
        if (nuevosPartidos == null) {
            throw new IllegalArgumentException("La lista de partidos no puede ser null");
        }
        partidos.clear();
        partidos.addAll(nuevosPartidos);
    }

}