import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int MAX_HISTORY_SIZE = 9;
    private ArrayList<Task> history = new ArrayList<>();

    @Override
    public void addTask(Task task) {

        if (task == null) {
            System.out.println("Задача не найдена");
        } else if (history.size() > MAX_HISTORY_SIZE) {
            history.removeFirst();
        }

        history.add(task);
    }

    @Override
    public void addAllTask(ArrayList<Task> allTasks) {
        if (allTasks == null) {
            System.out.println("Список задач пуст");
        } else {
            for (Task task : allTasks) {
                addTask(task);
            }
        }
    }

    @Override
    public ArrayList <Task> getHistiry() {
        return new ArrayList<>(history);
    }

    public void clearHistory() {
        history.clear();
    }

}
