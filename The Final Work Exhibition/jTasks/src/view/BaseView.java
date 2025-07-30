package view;

import java.util.List;

import controller.Controller;
import model.task.Task;

public abstract class BaseView {
    protected Controller controller; // Atributo común para todas las vistas.

    // Constructor
    public BaseView(Controller controller) {
        this.controller = controller;
    }

    // Métodos abstractos

    public abstract void displayTasks(List<Task> tasks);
    public abstract void init(); // Inicia la vista.
    public abstract void showMessage(String message); // Muestra un mensaje.
    public abstract void showErrorMessage(String errorMessage); // Muestra un mensaje de error.
    public abstract void end(); // Finaliza la vista.
    public abstract String showMenuAndGetOption(); // Método que define cómo se muestra el menú y se obtiene la opción.
}
