import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<SubTask> subTasks = new ArrayList<>();

    // Добавление элемента в список
    public void addSubTask(SubTask subTask){
        subTasks.add(subTask);
    }

    // Плучение задачи по индексу
    public SubTask getSubTask(int index){
        return subTasks.get(index);
    }

    // Удаление задачи
    public void removeSubTask(int index){
        subTasks.remove(index);
    }

    public int getSubTaskCount(){
        return subTasks.size();
    }

    public void  clearSubTasks(){
        subTasks.clear();
    }

    public void printSubTasks(){
        for(SubTask subTask : subTasks){
            System.out.println(subTask.getTitle());
        }
    }

    
}
