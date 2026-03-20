import java.util.ArrayList;

public interface HistoryManager {

    void addTask(Task task);
    ArrayList <Task> getHistiry();

    void addAllTask(ArrayList<Task> allTasks);
}
