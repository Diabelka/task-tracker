import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<Integer> subTasksIds = new ArrayList<>();

    // Добавление элемента в список
    public void addSubTask(int subTaskId) {
        subTasksIds.add(subTaskId);
    }

    // получение списка задач
    public List<Integer> getSubTasksIds() {
        return new ArrayList<>(subTasksIds);
    }

    // Вывод количества подзадач
    public int getSubTaskCount(){
        return subTasksIds.size();
    }

    // Удаление всех подзадач
    public void  clearAllSubTasksIds(){
        subTasksIds.clear();
        System.out.println("Все подзадачи удалены");
    }

    public  void clearSubTasksIds(int subTaskId){
        subTasksIds.remove(Integer.valueOf(subTaskId));
    }

}
