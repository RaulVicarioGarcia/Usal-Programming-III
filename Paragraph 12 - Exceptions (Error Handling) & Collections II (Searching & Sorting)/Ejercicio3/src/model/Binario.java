package model;

import java.io.*;
import java.util.List;

public class Binario {

    public void guardarBinario(String ruta, List<Libro> libros) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(libros);
        }
    }

    @SuppressWarnings("unchecked") // Silenciar advertencia.
    public List<Libro> cargarBinario(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<Libro>) ois.readObject();
        }
    }
}
