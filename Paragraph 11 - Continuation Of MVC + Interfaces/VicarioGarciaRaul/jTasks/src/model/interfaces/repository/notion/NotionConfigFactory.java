package model.interfaces.repository.notion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NotionConfigFactory {
    public static Properties cargarPropiedades() {
        Properties propiedades = new Properties();
        try (InputStream input = NotionConfigFactory.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se puede encontrar el archivo config.properties en el classpath.");
            }
            propiedades.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar config.properties.", ex);
        }
        return propiedades;
    }
}
