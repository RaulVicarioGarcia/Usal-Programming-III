package model;

import com.coti.tools.OpMat;
import com.coti.tools.Rutas;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartidoLoader {
    
    public static List<Partido> cargarPartidos() {

        List<Partido> partidos = new ArrayList<>();

        try {

            Path rutaArchivo = Rutas.pathToFileInDocuments("equipos.txt");
            File archivo = rutaArchivo.toFile();
            String[][] datos = OpMat.importFromDisk(archivo, "-");

        for (String[] fila : datos) {
            
                if (fila != null && fila.length >= 2) {
                    String equipoLocal = fila[0].trim();
                    String equipoVisitante = fila[1].trim();

                    if (!equipoLocal.isEmpty() && !equipoVisitante.isEmpty()) {
                        Equipo local = new Equipo(equipoLocal);
                        Equipo visitante = new Equipo(equipoVisitante);
                        Partido partido = new Partido(local, visitante);
                        partidos.add(partido);
            } } }
        
        } catch (Exception e) {
            System.err.println("Error al cargar los partidos desde el archivo: " + e.getMessage());
        }

        return partidos;

    }

}
