import java.util.HashMap;
import java.util.Objects;

public class Meneger {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int indexID = 1;

    // Добавление задач в hashMap
    public boolean addTask(Task task){
        if (!(task instanceof SubTask)){
        for (Task existingTask : tasks.values()) {
            if (existingTask.equals(task)) {
                System.out.println("ошибка: такая задача уже есть! (" + task.getTitle() + ")");
                return false;
            }
        }
            task.setId(indexID++);
            tasks.put(task.getId(), task);
            System.out.println("Задача добавлена с id: " + task.getId());
            return true;
    } else {
            task.setId(indexID++);
            tasks.put(task.getId(), task);
            System.out.println("Задача добавлена с id: " + task.getId());

            int epicIndex = ((SubTask) task).getEpikIndex();
            Task epic = tasks.get(epicIndex);
            ((Epic) epic).addSubTask(task.getId());
            return true;
        }
    }

    // Получение задачи по ID
    public Task getTask(int id){
        Task task = tasks.get(id);
        if (task == null){
            System.out.println("Задача с id: " + id + "не найдена");
        }
        return task;
    }

    // Вывод списка всех задач
    public void printAllTasks(){
        if (tasks.isEmpty()){
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список задач === ");
            System.out.println("Задачи: ");

            for (Task task : tasks.values()){
                if (!(task instanceof SubTask)) {
                    System.out.println("Задача с id:" + task.getId() + " - " + task.title + " - " + task.description);
                }
            }
            System.out.println("========================= ");
        }
    }

    // Вывод задачи по индексу
    public void printTasks(int indexID){
        if (tasks.isEmpty()){
            System.out.println(" Список задач пуст");
        } else {
            Task task = tasks.get(indexID);
            if (task instanceof Epic){
                System.out.println("Задача с id:" + task.getId() + " - " + task.title + " относится к классу Epic ");
            } else if (task instanceof SubTask) {
                int epicIndex = ((SubTask) task).getEpikIndex();
                Epic epic = (Epic) tasks.get(epicIndex);
                System.out.println("Задача с id:" + task.getId() + " - " + task.title +
                        " является подзадачей эпика c id:" + epic.getId());
            } else {
                System.out.println("Задача с id:" + task.getId() + " - " + task.title);
            }
        }
    }

    // Вывод подзадач
    public void printSubTasks(int indexID){
        if (tasks.isEmpty()){
            System.out.println(" Список задач пуст");
        } else {
            Task task = tasks.get(indexID);
            if (task instanceof Epic) {
                Epic epic = (Epic) tasks.get(indexID);
                for (int subIndex : epic.getSubTasksIds()){
                    Task subTask = tasks.get(subIndex);
                    System.out.println("Подзадача с id: " + subIndex + " - " + subTask.title + " - " + subTask.description);
                }
            }
        }
    }

    // Удаление всех задач
    public void deleteAllTasks(){
        for (Task task : tasks.values()){
            if (task instanceof Epic){
                ((Epic) task).clearAllSubTasksIds();
            }
            tasks.remove(task);
        }
    }

    public boolean removeTask(int index){
        Task task = tasks.get(index);

        if (task == null) {
            System.out.println("Задача с id: " + index + " не найдена");
            return false;
        }

        if (task instanceof Epic) {
            Epic epic = (Epic) task;

            for (Integer subTaskId : epic.getSubTasksIds()) {
                tasks.remove(subTaskId);
                System.out.println("Удалена подзадача c id = " + index);
            }
        }

        if (task instanceof SubTask) {
            int epicIndex = ((SubTask) task).getEpikIndex();
            Epic epic = (Epic) tasks.get(epicIndex);
            epic.clearSubTasksIds(index);
        }

        tasks.remove(index);
        System.out.println("Удалена задача c id = " + index);
        return true;
    }

    // Обновление задачи по индексу

    // Получение спика всех задач определенного эпика

}
