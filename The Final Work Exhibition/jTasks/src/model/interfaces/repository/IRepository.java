package model.interfaces.repository;

import model.task.Task;
import java.util.List;

public interface IRepository {

    Task addTask(Task t) throws RepositoryException;

    void removeTask(Task t) throws RepositoryException;

    void modifyTask(Task t) throws RepositoryException;

    List<Task> getAllTasks() throws RepositoryException;

}  