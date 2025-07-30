package model.interfaces.exporter;

import model.interfaces.exporter.csv.CsvExporter;
import model.interfaces.exporter.json.JsonExporter;

public class ExporterFactory {

    public static IExporter obtenerExportador(String tipo) throws ExporterException {

        switch (tipo.toLowerCase()) {

            case "csv":
                return new CsvExporter();
            case "json":
                return new JsonExporter();
            default:
                throw new ExporterException("Tipo de exportador no soportado: " + tipo, null);

        }
    
    }

}