import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private final List<SubTask> subTasks = new ArrayList<>();
    private int indexID = 1;


    // Добавление элемента в список
    public void addSubTask(SubTask subTask){
        subTask.setEpikTitle(title);
        subTask.setId(indexID++);
        subTasks.add(subTask);
        subTask.setStatus("NEW");
        recalculateStatus();
    }

    // Обновление статуса подзадачи (перезапись)


    // Вывод количества подзадач
    public int getSubTaskCount(){
        return subTasks.size();
    }

    // Плучение подзадачи по индексу
    public SubTask getSubTask(int index){
        // Проверка на валидность индекса
        if (index < 0 || index >= subTasks.size()) {
            System.out.println("Неверный индекс подзадачи: " + index);
            return null;
        } else{
            System.out.println("подзадача с индексом: " + index + " найдена");
            return subTasks.get(index);
        }
    }

    // Удаление подзадачи по индексу
    public boolean removeSubTask(int index){
        // Проверка на валидность индекса
        if (index < 0 || index >= subTasks.size()) {
            System.out.println("Неверный индекс подзадачи: " + index);
            return false;
        }

        subTasks.remove(index);
        recalculateStatus();
        System.out.println("подзадача с индексом: " + index + " удалена");
        return true;
    }

    // Удаление всех подзадач
    public void  clearSubTasks(){
        subTasks.clear();
    }

    // Вывод всех подзадач
    public  void printAllSubTasks(){
        for(SubTask subTask : subTasks){
            System.out.println(subTask.getTitle());
        }
    }

    @Override
    public void setStatus(String status) {
        throw new UnsupportedOperationException("Статус эпика нельзя изменить вручную! Он зависит от подзадач");
    }

    // Определение статуса для Epic на основании статусов subTasks
    public void recalculateStatus(){
        if (subTasks.isEmpty()) {
            super.setStatus("New");
            return;
        }

        long inProgressCount = subTasks.stream()
                .filter(t -> "IN_PROGRESS".equals(t.getStatus()))
                .count();

        if (inProgressCount > 0) {
            super.setStatus("IN_PROGRESS");
        }
        else {
            long doneCount = subTasks.stream()
                    .filter(t -> "Done".equals(t.getStatus()))
                    .count();

            if (doneCount == subTasks.size()) {
                super.setStatus("DONE");
            } else if (doneCount > 0) {
                super.setStatus("IN_PROGRESS");
            }  else {
                super.setStatus("NEW");
            }
        }
    }
}
