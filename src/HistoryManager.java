import java.util.ArrayList;
import java.util.List;

public interface HistoryManager {

    void add(Task task);
    void remove(int index);
    List <Task> getHistory();
}
