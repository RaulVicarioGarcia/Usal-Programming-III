package model.task;

import java.io.Serializable;
import java.util.*;

public class Task implements Serializable {

    // Asegúrate de incluir un serialVersionUID para controlar la compatibilidad de versiones
    private static final long serialVersionUID = 1L;

    // Atributos
    private int identificador;
    private String titulo;
    private Date fecha;
    private String contenido;
    private int prioridad;
    private int duracionEstimada;
    private boolean completada;

    // Constructor
    public Task(int identificador, String titulo, Date fecha, String contenido, int prioridad, int duracionEstimada, boolean completada) {
        // Si el identificador es 0, genera uno nuevo
        this.identificador = (identificador == 0) ? IdentificadorSingleton.getInstancia().generarNuevoIdentificador() : identificador;
        this.titulo = titulo;
        this.fecha = fecha;
        this.contenido = contenido;
        this.prioridad = prioridad;
        this.duracionEstimada = duracionEstimada;
        this.completada = completada;
    }

    // Getters y Setters
    public int getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String contenido) {
        this.contenido = contenido;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public void setFecha(Date fecha) {
        this.fecha=fecha;
    }

    @Override
    public String toString() {
        return "Tarea [ID=" + identificador + ", Título=" + titulo + ", Fecha=" + fecha +
                ", Contenido=" + contenido + ", Prioridad=" + prioridad +
                ", Duración=" + duracionEstimada + " mins, Completada=" + completada + "]";
    }
}
