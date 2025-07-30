package model;

import java.util.Comparator;

public class LibroComparator implements Comparator<Libro> {
    @Override
    public int compare(Libro l1, Libro l2) {
        // Comparar primero por autor
        int autorCompare = l1.getAutor().compareTo(l2.getAutor());
        // Si los autores son iguales, comparar por año
        if (autorCompare == 0) {
            return Integer.compare(l1.getAnoPrimeraPublicacion(), l2.getAnoPrimeraPublicacion());
        }
        return autorCompare;
    }
}