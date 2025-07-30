package model;

import com.google.gson.Gson;
import com.coti.tools.OpMat;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Importar {

    public List<Libro> importarJSON(Path ruta) throws RepositoryException {
        try {
        String json = Files.readString(ruta, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Libro[] librosArray = gson.fromJson(json, Libro[].class);
        return new ArrayList<>(List.of(librosArray)); } catch (Exception e) {

            throw new RepositoryException("No se pudo importar desde JSON",e);

        }
    }

    public static List<Libro> importarCSV(Path ruta, String delimitador) throws RepositoryException {
        try {
            String[][] datosCSV = OpMat.importFromDisk(ruta.toFile(), delimitador);
            List<Libro> libros = new ArrayList<>();
            for (int i = 1; i < datosCSV.length; i++) {
                String titulo = datosCSV[i][0];
                String autor = datosCSV[i][1];
                int anio = Integer.parseInt(datosCSV[i][2]);
                libros.add(new Libro(titulo, autor, anio));
            }
            return libros;
        } catch (Exception e) {
            throw new RepositoryException("Error al importar datos desde CSV.", e);
        }
    }

    public static List<Libro> importarXML(Path ruta) throws RepositoryException {
        try {
            List<Libro> libros = new ArrayList<>();
            String xml = Files.readString(ruta, StandardCharsets.UTF_8);

            String[] librosXML = xml.split("<libro>");
            for (int i = 1; i < librosXML.length; i++) {
                String libroXML = librosXML[i];
                String titulo = libroXML.split("<titulo>")[1].split("</titulo>")[0];
                String autor = libroXML.split("<autor>")[1].split("</autor>")[0];
                int anio = Integer.parseInt(libroXML.split("<anio>")[1].split("</anio>")[0]);
                libros.add(new Libro(titulo, autor, anio));
            }
            return libros;
        } catch (Exception e) {
            throw new RepositoryException("Error al importar datos desde XML.", e);
        }
    }
}
