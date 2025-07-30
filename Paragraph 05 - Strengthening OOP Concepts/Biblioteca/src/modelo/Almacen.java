package modelo;

public class Almacen {

    private Libro[] libros;
    int librosNuevos=0;
    int ritmoDeLectura=1;

    public Almacen(int capacidad) {

        libros = new Libro[capacidad];

    }

    public void nuevoLibro(Libro libro) {

        if (librosNuevos < libros.length) {

            libros[librosNuevos] = libro;
            librosNuevos++;

            System.out.println("Libro añadido correctamente.");

        } else {

            System.out.println("Almacén lleno. No se pueden añadir más libros.");

        } }

    public void marcarRitmoDeLectura (int ritmo) {

        if (ritmo > 0) {
            this.ritmoDeLectura = ritmo;
            System.out.println("Ritmo de lectura actualizado: " + ritmo + " páginas/minuto");
        } else {
            System.out.println("Ritmo no válido. Debe ser mayor que 0.");
        }

    }

    public void mostrarLibros() {

        System.out.println("| Título | Año | Autor | Premio Planeta | Páginas | Precio |");
        for (int i = 0; i < librosNuevos; i++) {
            System.out.println(libros[i]);
        }
    
    } }   
