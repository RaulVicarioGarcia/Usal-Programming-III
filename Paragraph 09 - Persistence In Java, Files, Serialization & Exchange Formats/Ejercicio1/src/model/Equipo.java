package model;

import java.util.Objects;

public class Equipo {
    
    private final String nombre;
    
    public Equipo (String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacío");
        }
        this.nombre = nombre.trim();

    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("| %s |", nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

}
