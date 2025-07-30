package model.interfaces.repository.notion;

import java.util.Properties;

public class NotionConfigLoader {

    // Propiedades cargadas dinámicamente desde argumentos o archivo.
    private static Properties propiedades;

    // Método para cargar las propiedades desde un archivo de configuración.
    public static void cargarDesdeArchivo() {
        propiedades = NotionConfigFactory.cargarPropiedades();
    }

    // Método para inicializar propiedades manualmente desde argumentos.
    public static void cargarDesdeArgumentos(String apiKey, String databaseId) {
        propiedades = new Properties();
        propiedades.setProperty("notion.api.key", apiKey);
        propiedades.setProperty("notion.database.id", databaseId);
    }

    // Método para obtener la clave de API.
    public static String obtenerClaveApiNotion() {
        if (propiedades == null) {
            throw new RuntimeException("Configuración no inicializada. Llame a cargarDesdeArchivo o cargarDesdeArgumentos.");
        }
        return propiedades.getProperty("notion.api.key");
    }

    // Método para obtener el ID de la base de datos.
    public static String obtenerIdBaseDatosNotion() {
        if (propiedades == null) {
            throw new RuntimeException("Configuración no inicializada. Llame a cargarDesdeArchivo o cargarDesdeArgumentos.");
        }
        return propiedades.getProperty("notion.database.id");
    }
}
