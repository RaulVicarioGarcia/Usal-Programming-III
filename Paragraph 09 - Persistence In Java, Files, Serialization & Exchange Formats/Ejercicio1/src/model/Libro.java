package model;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable{

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private int anoPrimeraPublicacion;

    // Constructor facil.
    public Libro(String titulo, String autor, int anoPrimeraPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPrimeraPublicacion = anoPrimeraPublicacion;
    }

    // Getters y setters
    public void setAnoPrimeraPublicacion(int anoPrimeraPublicacion) {
        this.anoPrimeraPublicacion = anoPrimeraPublicacion;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPrimeraPublicacion() {
        return anoPrimeraPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return autor + " | " + titulo + " | " + anoPrimeraPublicacion;
    }

    // Métodos equals y hashCode para comparar libros
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return anoPrimeraPublicacion == libro.anoPrimeraPublicacion &&
                Objects.equals(titulo, libro.titulo) &&
                Objects.equals(autor, libro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, anoPrimeraPublicacion);
    }
}
