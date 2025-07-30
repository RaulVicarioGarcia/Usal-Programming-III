package model;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.coti.tools.OpMat;

public class Exportar {

    public static void exportarCSV(Path ruta, List<Libro> libros, String delimitador) throws Exception {
        
            String[][] datosCSV = new String[libros.size() + 1][3];

            datosCSV[0][0] = "Titulo";
            datosCSV[0][1] = "Autor";
            datosCSV[0][2] = "Año";

            for (int i = 0; i < libros.size(); i++) {
                Libro libro = libros.get(i);
                datosCSV[i + 1][0] = libro.getTitulo();
                datosCSV[i + 1][1] = libro.getAutor();
                datosCSV[i + 1][2] = String.valueOf(libro.getAnoPrimeraPublicacion());
            }

            File archivo = ruta.toFile();
            OpMat.exportToDisk(datosCSV, archivo, delimitador);

    }

    public static void exportarJSON(Path ruta, List<Libro> libros) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(libros);
        Files.write(ruta, json.getBytes(StandardCharsets.UTF_8));
    }

    public static void exportarXML(Path ruta, List<Libro> libros) throws IOException {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<libros>\n");

        for (Libro libro : libros) {
            xmlBuilder.append("  <libro>\n")
                      .append("    <titulo>").append(libro.getTitulo()).append("</titulo>\n")
                      .append("    <autor>").append(libro.getAutor()).append("</autor>\n")
                      .append("    <anio>").append(libro.getAnoPrimeraPublicacion()).append("</anio>\n")
                      .append("  </libro>\n");
        }

        xmlBuilder.append("</libros>");

        try (FileWriter writer = new FileWriter(ruta.toFile())) {
            writer.write(xmlBuilder.toString());
        }
    }
}
