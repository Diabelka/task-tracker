import java.util.HashMap;
import java.util.Objects;

public class Meneger {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int indexID = 1;

    // Добавление задач в hashMap
    public boolean addTask(Task task){
        for (Task existingTask : tasks.values()){
            if (existingTask.equals(task)){
                System.out.println("ошибка: такая задача уже есть! (" + task.getTitle() + ")");
                return false;
            }
        }

        task.setId(indexID++);
        tasks.put(task.getId(), task);
        System.out.println("Задача добавлена с id: " + task.getId());
        return true;
    }

    // Получение задачи по ID
    public Task getTask(int id){
        Task task = tasks.get(id);
        if (task == null){
            System.out.println("Задача с id: " + id + "не найдена");
        }
        return task;
    }

    // Получение списка всех задач
    public void printAllTasks(){
        System.out.println("=== Список всех задач === ");
        for (Task task : tasks.values()){
            System.out.println(task.title + " - " + task.description);
        }
        System.out.println("========================= ");
    }

    // Удаление всех задач
    public void deleteAllTasks(){
        for (Task task : tasks.values()){
            tasks.remove(task);
        }
    }

    public void deleteTask(int id){
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Задача с id = " + id + " не найдена");
        } else {
            tasks.remove(id);
            System.out.println("Задача с id = " + id + " удалена из менеджера");
        }

    }

    // Получение задачи по индексу

    // Обновление задачи по индексу

    // Получение спика всех задач определенного эпика

}
