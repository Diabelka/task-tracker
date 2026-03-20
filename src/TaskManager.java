import java.util.ArrayList;

public interface TaskManager {

    // Добавление задач в hashMap
    boolean addTask(Task task);

    // Обновление задачи по индексу
    boolean updateTask(int indexTask, Task newTask);

    // Измененипе статуса эпика
    boolean recalculateEpicStatus(int epicIndex);

    // Получение задачи по ID
    Task getTask(int id);

    // Вывод списка всех задач
    ArrayList<Task> getAllTasks();

    // Получение подзадач
    ArrayList<Task> getAllSubTasks(int epicIndex);

    // Удаление всех задач
    void deleteAllTasks();

    // удаление задачи по индексу
    boolean removeTask(int index);

    ArrayList<Task> getHistoryTasks();

}
