import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    private int indexID = 1;
    private final HistoryManager history = TaskManagers.getDefaultHistory();

    // Добавление задач в hashMap
    @Override
    public boolean addTask(Task task) {
        if (task instanceof SubTask) {
            int epicIndex = ((SubTask) task).getEpicIndex();

            // Проверяем, что эпик существует
            Task epic = tasks.get(epicIndex);
            if (epic == null || !(epic instanceof Epic)) {
                System.out.println("Эпик с id: " + epicIndex + " не найден");
                return false;
            }

            // Проверяем на дубликаты В этом эпике
            for (Task existingTask : tasks.values()) {
                if (existingTask instanceof SubTask) {
                    SubTask existingSubTask = (SubTask) existingTask;
                    if (existingSubTask.getEpicIndex() == epicIndex &&
                            existingSubTask.equals(task)) {
                        System.out.println("Ошибка: такая подзадача уже есть в этом эпике! (" + task.getTitle() + ")");
                        return false;
                    }
                }
            }

            // Присваиваем ID и добавляем в общую карту
            task.setId(indexID++);
            tasks.put(task.getId(), task);
            System.out.println("Задача добавлена с id: " + task.getId());

            // Добавляем ВЕРНЫЙ ID в эпик
            ((Epic) epic).addSubTask(task.getId());

            return true;

        } else {
            // Для обычных задач и эпиков — глобальная проверка на дубликаты
            for (Task existingTask : tasks.values()) {
                if (existingTask.equals(task)) {
                    System.out.println("Ошибка: такая задача уже есть! (" + task.getTitle() + ")");
                    return false;
                }
            }

            task.setId(indexID++);
            tasks.put(task.getId(), task);
            System.out.println("Задача добавлена с id: " + task.getId());
            return true;
        }
    }

    // Обновление задачи по индексу
    @Override
    public boolean updateTask(int indexTask, Task newTask) {
        Task task = tasks.get(indexTask);
        if (task == null) {
            System.out.println("Задача с id: " + indexTask + " не найдена");
            return false;
        } else {
            newTask.setId(indexTask);
            tasks.put(indexTask, newTask);

            System.out.println("Задача с id: " + indexTask + " обновлена");
            System.out.println("Новый статус задачи: " + newTask.getStatus());

            if (newTask instanceof SubTask) {
                int epicIndex = ((SubTask) newTask).getEpicIndex();
                Task epic = tasks.get(epicIndex);
                if (!(epic instanceof Epic)) {
                    System.out.println("Эпик с id: " + epicIndex + " не найден");
                    return false;
                }
                ((Epic) epic).addSubTask(newTask.getId());
                recalculateEpicStatus(epicIndex);
            }
            return true;
        }
    }

    // Изменение статуса эпика
    @Override
    public boolean recalculateEpicStatus(int epicIndex) {
        Epic epic = ((Epic) tasks.get(epicIndex));

        if (epic == null) {
            System.out.println("Задача с id: " + epicIndex + " не найдена");
            return false;
        }

        int statusCountNew = 0;
        int statusCountDone = 0;
        //String subTaskStatus = "NEW";

        for (int indexSubTask : epic.getSubTasksIds()) {
            Task subTask = tasks.get(indexSubTask);
            if (subTask == null) {
                continue;  // Пропускаем несуществующие подзадачи
            }

            if (Status.NEW.equals(subTask.getStatus())) {
                statusCountNew++;
            } else if (Status.DONE.equals(subTask.getStatus())) {
                statusCountDone++;
            } else {
                break; // Если хотябы одна задача имеет статус IN_PROGRESS, то и у эпика статус IN_PROGRESS
            }
        }

        if (statusCountNew == epic.getSubTaskCount()) {
            if (!(Status.NEW.equals(epic.getStatus()))) {
                epic.setStatus(Status.NEW);
                return true;
            }
        } else if (statusCountDone == epic.getSubTaskCount()) {
            if (!(Status.DONE.equals(epic.getStatus()))) {
                epic.setStatus(Status.DONE);
                return true;
            }
        } else {
            if (!(Status.IN_PROGRESS.equals(epic.getStatus()))) {
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
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Task> getAllSubTasks(int epicIndex) {
        ArrayList<Task> subTasks = new ArrayList<>();

        Task task = tasks.get(epicIndex);
        if (task == null) {
            System.out.println("Задача с id: " + epicIndex + " не найдена");
            return subTasks;
        }

        if (!(task instanceof Epic)) {
            System.out.println("Задача с id: " + epicIndex + " не является Epic");
            return subTasks;
        }

        Epic epic = (Epic) task;
        for (int subIndex : epic.getSubTasksIds()) {
            subTasks.add(getTask(subIndex));  // getTask сам добавит в историю
        }

        return subTasks;
    }

    // Удаление всех задач
    @Override
    public void deleteAllTasks() {
        for (Task task : tasks.values()) {
            if (task instanceof Epic) {
                ((Epic) task).clearAllSubTasksIds();
            }
        }

        tasks.clear();
        history.clearHistory();
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
                epic.setStatus(Status.NEW);
                updateTask(epicIndex, epic);
            }
        }

        return true;
    }

    @Override
    public ArrayList<Task> getHistoryTasks() {
        return history.getHistory();
    }
}


