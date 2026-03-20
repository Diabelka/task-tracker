import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int MAX_HISTORY_SIZE = 10;
    private final ArrayList<Task> history = new ArrayList<>();

    @Override
    public void addTask(Task task) {

        if (task == null) {
            System.out.println("Задача не найдена");
            return;
        }

        history.removeIf(t ->  t.equals(task));

        if (history.size() >= MAX_HISTORY_SIZE) {
            history.remove(0);
        }

        history.add(task);
    }


    @Override
    public ArrayList <Task> getHistory() {
        return new ArrayList<Task>(history);
    }

    @Override
    public void clearHistory() {
        history.clear();
    }
}
