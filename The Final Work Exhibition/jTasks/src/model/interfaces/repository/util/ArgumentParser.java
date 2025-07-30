package model.interfaces.repository.util;

import java.util.HashMap;
import java.util.Map;

public class ArgumentParser {

    // Mapa para almacenar los argumentos procesados.
    private final Map<String, String[]> argumentos = new HashMap<>();

    // Constructor que recibe y procesa los argumentos.
    public ArgumentParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            // Identificar los argumentos que comienzan con "--".
            if (args[i].startsWith("--")) {
                String key = args[i].substring(2); // Remover el prefijo "--".
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    // Si hay un valor asociado, almacenarlo.
                    argumentos.put(key, new String[]{args[i + 1]});
                    i++;
                } else {
                    // Si no hay un valor, almacenarlo como vacío.
                    argumentos.put(key, new String[0]);
                }
            }
        }
    }

    // Método para verificar si existe un argumento.
    public boolean tieneArgumento(String key) {
        return argumentos.containsKey(key);
    }

    // Método para obtener el valor de un argumento.
    public String[] obtenerArgumento(String key) {
        return argumentos.getOrDefault(key, new String[0]);
    }
}
