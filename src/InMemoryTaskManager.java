import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int indexID = 1;
    HistoryManager history = TaskMamagers.getDefaultHistory();

    // Добавление задач в hashMap
    @Override
    public boolean addTask(Task task) {
        if (!(task instanceof SubTask)) {
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

            int epicIndex = ((SubTask) task).getEpicIndex();
            Task epic = tasks.get(epicIndex);
            ((Epic) epic).addSubTask(task.getId());
            return true;
        }
    }

    // Обновление задачи по индексу
    @Override
    public boolean updateTask(int indexTask, Task newTask) {
        Task task = tasks.get(indexTask);
        if (task == null) {
            System.out.println("Задача с id: " + indexTask + "не найдена");
            return false;
        } else {
            newTask.setId(indexTask);
            tasks.put(indexTask, newTask);

            System.out.println("Задача с id: " + indexTask + " обновлена");
            System.out.println("Статус задачи " + task.getStatus());

            if (task instanceof SubTask) {
                int epicIndex = ((SubTask) task).getEpicIndex();
                Task epic = tasks.get(epicIndex);
                ((Epic) epic).addSubTask(task.getId());
                recalculateEpicStatus(epicIndex);
            }
            return true;
        }
    }

    // Измененипе статуса эпика
    @Override
    public boolean recalculateEpicStatus(int epicIndex) {
        Epic epic = ((Epic) tasks.get(epicIndex));

        if (epic == null) {
            System.out.println("Задача с id: " + epicIndex + "не найдена");
            return false;
        }

        String oldEpicStatus = epic.getStatus().toString();
        int statusCountNew = 0;
        int statusCountDone = 0;
        //String subTaskStatus = "NEW";

        for (int indexSubTask : epic.getSubTasksIds()) {
            Task subTask = tasks.get(indexSubTask);
            if (Status.NEW.equals(subTask.status)) {
                statusCountNew++;
            } else if (Status.DONE.equals(subTask.status)) {
                statusCountDone++;
            } else {
                break;
            }
        }

        if (statusCountNew == epic.getSubTaskCount()) {
            if (!(Status.NEW.equals(epic.status))) {
                epic.setStatus(Status.NEW);
                return true;
            }
        } else if (statusCountDone == epic.getSubTaskCount()) {
            if (!(Status.DONE.equals(epic.status))) {
                epic.setStatus(Status.DONE);
                return true;
            }
        } else {
            if (!(Status.IN_PROGRESS.equals(epic.status))) {
                epic.setStatus(Status.IN_PROGRESS);
                return true;
            }
        }
        return false;
    }

    // Получение задачи по ID
    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Задача с id: " + id + " не найдена");
            return null;
        }
        history.addTask(task);
        return task;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        history.addAllTask(new ArrayList<>(tasks.values()));
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Task> getAllSubTasks(int epicIndex) {
        ArrayList<Task> subTasks = new ArrayList<>();

        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            Task task = tasks.get(epicIndex);
            if (task instanceof Epic) {
                Epic epic = (Epic) tasks.get(epicIndex);
                for (int subIndex : epic.getSubTasksIds()) {
                    subTasks.add(getTask(subIndex));
                }
            }
        }
        history.addAllTask(subTasks);
        return subTasks;
    }

    // Удаление всех задач
    @Override
    public void deleteAllTasks() {
        for (Task task : tasks.values()) {
            if (task instanceof Epic) {
                ((Epic) task).clearAllSubTasksIds();
            }
            tasks.remove(task);
        }
    }

    // удаление задачи по индексу
    @Override
    public boolean removeTask(int index) {
        Task task = tasks.get(index);

        if (task == null) {
            System.out.println("Задача с id: " + index + " не найдена");
            return false;
        }

        if (task instanceof Epic) {
            Epic epic = (Epic) task;

            for (Integer subTaskId : epic.getSubTasksIds()) {
                tasks.remove(subTaskId);
                System.out.println("Удалена подзадача c id = " + subTaskId);
            }
        }

        tasks.remove(index);
        System.out.println("Удалена задача c id = " + index);

        if (task instanceof SubTask) {
            int epicIndex = ((SubTask) task).getEpicIndex();
            Epic epic = (Epic) tasks.get(epicIndex);
            epic.clearSubTasksIds(index);

            if (epic.getSubTaskCount() == 0) {
                epic.status = Status.NEW;
                updateTask(epicIndex, epic);
            }
        }

        return true;
    }

    @Override
    public ArrayList<Task> getHistoryTasks() {
        return history.getHistiry();
    }
}


