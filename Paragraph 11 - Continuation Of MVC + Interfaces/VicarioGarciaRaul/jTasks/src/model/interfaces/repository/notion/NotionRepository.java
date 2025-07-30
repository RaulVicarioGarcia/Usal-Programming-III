package model.interfaces.repository.notion;

import notion.api.v1.NotionClient;
import notion.api.v1.http.OkHttp5Client;
import notion.api.v1.logging.Slf4jLogger;
import notion.api.v1.model.databases.QueryResults;
import notion.api.v1.model.pages.Page;
import notion.api.v1.model.pages.PageParent;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.pages.PageProperty.RichText;
import notion.api.v1.model.pages.PageProperty.RichText.Text;
import notion.api.v1.request.databases.QueryDatabaseRequest;
import notion.api.v1.request.pages.CreatePageRequest;
import notion.api.v1.request.pages.UpdatePageRequest;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import model.task.Task;
import model.interfaces.repository.IRepository;
import model.interfaces.repository.RepositoryException;

public class NotionRepository implements IRepository {

    private final NotionClient client;
    private final String databaseId;
    private final String titleColumnName = "Identificador"; // Columna clave única de tipo Title en Notion.

    public NotionRepository(String apiKey, String databaseId) throws RepositoryException {
        try {
            this.databaseId = databaseId;

            // Inicializar cliente de Notion con la API_KEY
            this.client = new NotionClient(apiKey);

            // Configurar cliente HTTP y logger
            client.setHttpClient(new OkHttp5Client(60000, 60000, 60000));
            client.setLogger(new Slf4jLogger());

        } catch (Exception e) {
            throw new RepositoryException("Error al inicializar el cliente de Notion.", e);
        }
    }

    // Crear una tarea en la base de datos.

    @Override
    public Task addTask(Task task) throws RepositoryException {
        if (task == null) {
            throw new RepositoryException("La tarea no puede ser nula.");
        }

        // Generar un nuevo ID sin depender del ID que viene en task
        int newId = generateNewId();

        String identificadorStr = String.valueOf(newId);

        // Verificamos que no exista este ID (en teoría no debería, pero por seguridad
        // se deja)
        if (findPageIdByIdentifier(identificadorStr) != null) {
            throw new RepositoryException("Ya existe una tarea con el Identificador: " + identificadorStr);
        }

        try {
            Map<String, PageProperty> properties = Map.of(
                    "Identificador", createTitleProperty(identificadorStr),
                    "Titulo", createRichTextProperty(task.getTitulo()),
                    "Fecha", createDateProperty(task.getFecha()),
                    "Contenido", createRichTextProperty(task.getContenido()),
                    "Prioridad", createNumberProperty(task.getPrioridad()),
                    "Duracion Estimada", createNumberProperty(task.getDuracionEstimada()),
                    "Completada", createCheckboxProperty(task.isCompletada()));

            PageParent parent = PageParent.database(databaseId);
            CreatePageRequest request = new CreatePageRequest(parent, properties);
            client.createPage(request);

            // Retornar una nueva Task con el ID recién asignado
            return new Task(newId, task.getTitulo(), task.getFecha(), task.getContenido(),
                    task.getPrioridad(), task.getDuracionEstimada(), task.isCompletada());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Error al crear la tarea en Notion.", e);
        }
    }

    private int generateNewId() throws RepositoryException {
        List<Task> existingTasks = getAllTasks();
        int maxId = 0;
        for (Task t : existingTasks) {
            if (t.getIdentificador() > maxId) {
                maxId = t.getIdentificador();
            }
        }
        // El nuevo ID será el máximo existente + 1
        return maxId + 1;
    }

    // Obtener todas las tareas directamente desde Notion.

    @Override
    public List<Task> getAllTasks() throws RepositoryException {
        try {
            List<Task> tasks = new ArrayList<>();
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);

            for (Page page : queryResults.getResults()) {
                // Ignorar las páginas archivadas
                if (page.getArchived() != null && page.getArchived()) {
                    continue;
                }

                Map<String, PageProperty> properties = page.getProperties();
                Task task = mapPageToTask(page.getId(), properties);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (Exception e) {
            throw new RepositoryException("Error al obtener las tareas desde Notion.", e);
        }
    }

    // Actualizar una tarea por su identificador.

    @Override
    public void modifyTask(Task task) throws RepositoryException {
        if (task == null) {
            throw new RepositoryException("La tarea no puede ser nula.");
        }
        try {
            String pageId = findPageIdByIdentifier(String.valueOf(task.getIdentificador()));
            if (pageId == null) {
                throw new RepositoryException(
                        "No se encontro una tarea con el Identificador: " + task.getIdentificador());
            }

            Map<String, PageProperty> updatedProperties = Map.of(
                    "Titulo", createRichTextProperty(task.getTitulo()),
                    "Fecha", createDateProperty(task.getFecha()),
                    "Contenido", createRichTextProperty(task.getContenido()),
                    "Prioridad", createNumberProperty(task.getPrioridad()),
                    "Duracion Estimada", createNumberProperty(task.getDuracionEstimada()),
                    "Completada", createCheckboxProperty(task.isCompletada()));

            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, updatedProperties);
            client.updatePage(updateRequest);

        } catch (Exception e) {
            throw new RepositoryException("Error al actualizar la tarea en Notion.", e);
        }
    }

    // Eliminar (archivar) una tarea por identificador.

    @Override
    public void removeTask(Task task) throws RepositoryException {
        if (task == null) {
            throw new RepositoryException("La tarea no puede ser nula.");
        }
        try {
            String pageId = findPageIdByIdentifier(String.valueOf(task.getIdentificador()));
            if (pageId == null) {
                throw new RepositoryException(
                        "No se encontró una tarea con el Identificador: " + task.getIdentificador());
            }

            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, Collections.emptyMap(), true);
            client.updatePage(updateRequest);

        } catch (Exception e) {
            throw new RepositoryException("Error al archivar la tarea en Notion.", e);
        }
    }

    // Métodos auxiliares.

    private String findPageIdByIdentifier(String identifier) throws RepositoryException {
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults queryResults = client.queryDatabase(queryRequest);

            for (Page page : queryResults.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                if (properties.containsKey(titleColumnName) &&
                        properties.get(titleColumnName).getTitle().get(0).getText().getContent().equals(identifier)) {
                    return page.getId();
                }
            }
            return null;
        } catch (Exception e) {
            throw new RepositoryException("Error al buscar la tarea por identificador en Notion.", e);
        }
    }

    private Task mapPageToTask(String pageId, Map<String, PageProperty> properties) throws RepositoryException {
        try {
            // Identificador
            PageProperty identProperty = properties.get("Identificador");
            if (identProperty == null || identProperty.getTitle() == null || identProperty.getTitle().isEmpty()
                    || identProperty.getTitle().get(0).getText() == null
                    || identProperty.getTitle().get(0).getText().getContent() == null) {
                throw new RepositoryException(
                        "La propiedad 'Identificador' está ausente o no esta correctamente definida.");
            }
            int id = Integer.parseInt(identProperty.getTitle().get(0).getText().getContent());

            // Título
            PageProperty tituloProp = properties.get("Titulo");
            String titulo = (tituloProp != null && tituloProp.getRichText() != null
                    && !tituloProp.getRichText().isEmpty()
                    && tituloProp.getRichText().get(0).getText() != null)
                            ? tituloProp.getRichText().get(0).getText().getContent()
                            : "";

            // Fecha
            Date fecha = null;
            PageProperty fechaProp = properties.get("Fecha");
            if (fechaProp != null && fechaProp.getDate() != null && fechaProp.getDate().getStart() != null) {
                String dateString = fechaProp.getDate().getStart();
                fecha = parseNotionDate(dateString);
            }

            // Contenido
            PageProperty contenidoProp = properties.get("Contenido");
            String contenido = (contenidoProp != null && contenidoProp.getRichText() != null
                    && !contenidoProp.getRichText().isEmpty()
                    && contenidoProp.getRichText().get(0).getText() != null)
                            ? contenidoProp.getRichText().get(0).getText().getContent()
                            : "";

            // Prioridad (número)
            PageProperty prioridadProp = properties.get("Prioridad");
            int prioridad = (prioridadProp != null && prioridadProp.getNumber() != null)
                    ? prioridadProp.getNumber().intValue()
                    : 0;

            // Duración Estimada
            PageProperty duracionProp = properties.get("Duracion Estimada");
            int duracionEstimada = (duracionProp != null && duracionProp.getNumber() != null)
                    ? duracionProp.getNumber().intValue()
                    : 0;

            // Completada
            PageProperty completadaProp = properties.get("Completada");
            boolean completada = (completadaProp != null && completadaProp.getCheckbox() != null)
                    ? completadaProp.getCheckbox()
                    : false;

            return new Task(id, titulo, fecha, contenido, prioridad, duracionEstimada, completada);
        } catch (Exception e) {
            throw new RepositoryException("Error al mapear la página de Notion a una tarea: " + e.getMessage(), e);
        }
    }

    private Date parseNotionDate(String dateString) throws Exception {
        try {
            // Intentar parsear como Instant (si incluye hora y zona horaria)
            Instant instant = Instant.parse(dateString);
            return Date.from(instant);
        } catch (Exception ex) {
            // Si falla, intentar parsear como LocalDate simple
            LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }

    private PageProperty createTitleProperty(String title) {
        RichText idText = new RichText();
        idText.setText(new Text(title));
        PageProperty idProperty = new PageProperty();
        idProperty.setTitle(Collections.singletonList(idText));
        return idProperty;
    }

    private PageProperty createRichTextProperty(String text) {
        RichText richText = new RichText();
        richText.setText(new Text(text));
        PageProperty property = new PageProperty();
        property.setRichText(Collections.singletonList(richText));
        return property;
    }

    private PageProperty createNumberProperty(Integer number) {
        PageProperty property = new PageProperty();
        property.setNumber(number);
        return property;
    }

    private PageProperty createDateProperty(Date date) {
        PageProperty property = new PageProperty();
        PageProperty.Date dateProperty = new PageProperty.Date();
        dateProperty.setStart(date.toInstant().toString());
        property.setDate(dateProperty);
        return property;
    }

    private PageProperty createCheckboxProperty(boolean checked) {
        PageProperty property = new PageProperty();
        property.setCheckbox(checked);
        return property;
    }
}
