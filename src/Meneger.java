import java.util.HashMap;
import java.util.Objects;

public class Meneger {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int indexID = 1;

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



}
