package model.interfaces.repository;

import model.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements IRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public Task addTask(Task t) throws RepositoryException {
        if (t == null) {
            throw new RepositoryException("La tarea no puede ser nula.");
        }
        tasks.add(t);
        return t;
    }

    @Override
    public void removeTask(Task t) throws RepositoryException {
        if (!tasks.remove(t)) {
            throw new RepositoryException("No se encontró la tarea para eliminar.");
        }
    }

    @Override
    public void modifyTask(Task t) throws RepositoryException {
        // Implementar la lógica para modificar tareas si es necesario
    }

    @Override
    public List<Task> getAllTasks() throws RepositoryException {
        return tasks;
    }
}
