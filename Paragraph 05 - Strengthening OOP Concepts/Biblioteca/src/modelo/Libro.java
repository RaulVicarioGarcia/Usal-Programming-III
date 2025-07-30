package modelo;

public class Libro {

    private Autor autor;
    private String titulo;
    private int anioDePublicacion;
    private int numeroDePaginas;
    private double precio;

    public Libro (Autor autor, String titulo, int anioDePublicacion, int numeroDePaginas, double precio) {

        this.autor=autor;
        this.titulo=titulo;
        this.anioDePublicacion=anioDePublicacion;
        this.numeroDePaginas=numeroDePaginas;
        this.precio=precio;

    }

    public Autor getAutor() {
        return autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getAnioDePublicacion() {
        return anioDePublicacion;
    }
    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }
    public double getPrecio() {
        return precio;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAnioDePublicacion(int anioDePublicacion) {
        this.anioDePublicacion = anioDePublicacion;
    }
    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {

        return ""+getTitulo()+" | "+getAnioDePublicacion()+" | "+autor.getNombre()+" | "+autor.getApellidos()+" | "+(autor.getPremioPlaneta() ? "Si" : "No") + " | " +
        numeroDePaginas + " | " + precio + " €";

    }

}