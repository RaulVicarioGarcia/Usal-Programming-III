package model.interfaces.repository.bin;

import model.interfaces.repository.IRepository;
import model.interfaces.repository.RepositoryException;
import model.task.IdentificadorSingleton;
import model.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryRepository implements IRepository {

    private final File file;

    
    public BinaryRepository() {
        this.file = new File("C:\\Users\\Pablo\\Documents\\tasks.bin");

        // Mensajes de depuración
        System.out.println("Ruta del archivo binario: " + file.getAbsolutePath());
        System.out.println("Archivo existe: " + file.exists());
        System.out.println("Archivo es legible: " + file.canRead());
        System.out.println("Archivo es escribible: " + file.canWrite());
    }


    @Override
    public Task addTask(Task t) throws RepositoryException {
        try {
            List<Task> tasks = getAllTasks();
            if (tasks.stream().anyMatch(task -> task.getIdentificador() == t.getIdentificador())) {
                throw new RepositoryException("Ya existe una tarea con el identificador: " + t.getIdentificador());
            }
            tasks.add(t);
            saveData(tasks);
            return t;
        } catch (Exception e) {
            e.printStackTrace(); // Esto imprime la traza completa del error en consola
            throw new RepositoryException("Error al añadir la tarea.", e);
        }
    }



    @Override
    public void removeTask(Task t) throws RepositoryException {
        try {
            List<Task> tasks = getAllTasks();
            tasks.removeIf(task -> task.getIdentificador() == t.getIdentificador());
            saveData(tasks);
        } catch (Exception e) {
            throw new RepositoryException("Error al eliminar la tarea.", e);
        }
    }

    @Override
    public void modifyTask(Task t) throws RepositoryException {
        try {
            List<Task> tasks = getAllTasks();
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getIdentificador() == t.getIdentificador()) {
                    tasks.set(i, t);
                    break;
                }
            }
            saveData(tasks);
        } catch (Exception e) {
            throw new RepositoryException("Error al modificar la tarea.", e);
        }
    }

    @Override
    public List<Task> getAllTasks() throws RepositoryException {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            List<Task> tasks = (List<Task>) ois.readObject();

            // Verifica duplicados
            Set<Integer> uniqueIds = new HashSet<>();
            for (Task task : tasks) {
                if (!uniqueIds.add(task.getIdentificador())) {
                    throw new RepositoryException("Se encontró un identificador duplicado al cargar las tareas: " + task.getIdentificador());
                }
            }

            // Actualiza el identificador máximo en IdentificadorSingleton
            int maxId = tasks.stream().mapToInt(Task::getIdentificador).max().orElse(0);
            IdentificadorSingleton.getInstancia().actualizarUltimoIdentificador(maxId);

            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Error al cargar tareas: fallo de E/S.", e);
        }
    }




    private void saveData(List<Task> tasks) throws RepositoryException {
        try {
            System.out.println("Ruta del archivo: " + file.getAbsolutePath());
            System.out.println("Archivo existe: " + file.exists());
            System.out.println("Archivo escribible: " + file.canWrite());

            // Intentar guardar las tareas
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(tasks);
                System.out.println("Tareas guardadas correctamente.");
            }
        } catch (FileNotFoundException e) {
            throw new RepositoryException("El archivo binario no se encontró. Verifica la ruta.", e);
        } catch (IOException e) {
            throw new RepositoryException("Error al guardar tareas en el archivo binario. Verifica permisos o espacio en disco.", e);
        }
    }



}
