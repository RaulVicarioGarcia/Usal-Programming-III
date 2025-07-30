package model.interfaces.exporter;

// Excepción personalizada para manejo de errores en exportación/importación.

public class ExporterException extends Exception {

    public ExporterException(String mensaje, Throwable causa) {

        super(mensaje, causa);

    }
    
    public ExporterException(String message) {
        super(message);
    }

}