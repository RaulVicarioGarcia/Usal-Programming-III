package model.task;

// Clase que implementa un singleton para manejar identificadores unicos
public class IdentificadorSingleton {
    // Instancia unica de la clase
    private static IdentificadorSingleton instancia;

    // Variable que almacena el ultimo identificador generado
    private int ultimoId;

    // Constructor privado para evitar la instanciacion directa
    private IdentificadorSingleton() {
        this.ultimoId = 0; // Inicia en 0 por defecto
    }

    // Metodo para obtener la instancia unica del singleton
    public static IdentificadorSingleton getInstancia() {
        // Si la instancia no existe, se crea una nueva
        if (instancia == null) {
            instancia = new IdentificadorSingleton();
        }
        // Retorna la instancia unica
        return instancia;
    }

    // Metodo sincronizado para generar un nuevo identificador unico
    public synchronized int generarNuevoIdentificador() {
        // Incrementa el ultimo identificador y lo retorna
        return ++ultimoId;
    }

    // Metodo sincronizado para actualizar el ultimo identificador si es mayor
    public synchronized void actualizarUltimoIdentificador(int id) {
        // Solo actualiza si el nuevo id es mayor al ultimo registrado
        if (id > ultimoId) {
            ultimoId = id;
        }
    }
}
