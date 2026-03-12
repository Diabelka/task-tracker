import java.util.HashMap;

public class Manager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private int indexID = 1;

    // Добавление задач в hashMap
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

            int epicIndex = ((SubTask) task).getEpikIndex();
            Task epic = tasks.get(epicIndex);
            ((Epic) epic).addSubTask(task.getId());
            return true;
        }
    }

    // Обновление задачи по индексу
    public boolean updateTask(int indexTask, Task newTask) {
        Task task = tasks.get(indexTask);
        if (task == null) {
            System.out.println("Задача с id: " + indexTask + "не найдена");
            return false;
        } else {
            removeTask(indexTask);
            tasks.put(indexTask, newTask);

            System.out.println("Задача с id: " + indexTask + " обновлена");
            System.out.println("Статус задачи " + task.getStatus());

            if (task instanceof SubTask) {
                int epicIndex = ((SubTask) task).getEpikIndex();
                Task epic = tasks.get(epicIndex);
                ((Epic) epic).addSubTask(task.getId());
                recalculateEpicStatus(epicIndex);
            }
            return true;
        }
    }

    // Измененипе статуса эпика
    public boolean recalculateEpicStatus(int epicIndex) {
        Epic epic = ((Epic) tasks.get(epicIndex));

        if (epic == null) {
            System.out.println("Задача с id: " + epicIndex + "не найдена");
            return false;
        }

        String oldEpicStatus = epic.getStatus().toString();
        int statusCauntNew = 0;
        int statusCauntDone = 0;
        String subTaskStatus = "NEW";

        for (int indexSubTask : epic.getSubTasksIds()) {
            Task subTask = tasks.get(indexSubTask);
            subTaskStatus = subTask.getStatus().toString();
            if (subTaskStatus.equals("NEW")) {
                statusCauntNew++;
            } else if (subTaskStatus.equals("DONE")) {
                statusCauntDone++;
            } else {
                break;
            }
        }

        if (statusCauntNew == epic.getSubTaskCount()) {
            if (!(oldEpicStatus.equals("NEW"))) {
                epic.setStatus(Task.Status.NEW);
                return true;
            }
        } else if (statusCauntDone == epic.getSubTaskCount()) {
            if (!(oldEpicStatus.equals("DONE"))) {
                epic.setStatus(Task.Status.DONE);
                return true;
            }
        } else {
            if (!(oldEpicStatus.equals("IN_PROGRESS"))) {
                epic.setStatus(Task.Status.IN_PROGRESS);
                return true;
            }
        }
        return false;
}

// Получение задачи по ID
public Task getTask(int id) {
    Task task = tasks.get(id);
    if (task == null) {
        System.out.println("Задача с id: " + id + "не найдена");
    }
    return task;
}

// Вывод списка всех задач
public void printAllTasks() {
    if (tasks.isEmpty()) {
        System.out.println(" Список задач пуст");
    } else {
        System.out.println("=== Список задач === ");
        System.out.println("Задачи: ");

        for (Task task : tasks.values()) {
            if (!(task instanceof SubTask)) {
                System.out.println("Задача с id:" + task.getId() + " - " + task.title + " - " + task.description +
                        " - " + task.status.toString());
            }
        }
        System.out.println("========================= ");
    }
}

// Вывод задачи по индексу
public void printTasks(int indexID) {
    if (tasks.isEmpty()) {
        System.out.println(" Список задач пуст");
    } else {
        Task task = tasks.get(indexID);
        if (task instanceof Epic) {
            System.out.println("Задача с id:" + task.getId() + " - " + task.title + " относится к классу Epic ");
        } else if (task instanceof SubTask) {
            int epicIndex = ((SubTask) task).getEpikIndex();
            Epic epic = (Epic) tasks.get(epicIndex);
            System.out.println("Задача с id:" + task.getId() + " - " + task.title +
                    " является подзадачей эпика c id:" + epic.getId());
        } else {
            System.out.println("Задача с id:" + task.getId() + " - " + task.title + "-" + task.getStatus());
        }
    }
}

// Вывод подзадач
public void printSubTasks(int indexID) {
    if (tasks.isEmpty()) {
        System.out.println(" Список задач пуст");
    } else {
        Task task = tasks.get(indexID);
        if (task instanceof Epic) {
            Epic epic = (Epic) tasks.get(indexID);
            for (int subIndex : epic.getSubTasksIds()) {
                Task subTask = tasks.get(subIndex);
                System.out.println("Подзадача с id: " + subIndex + " - " + subTask.title + " - " +
                        subTask.description + "-" + subTask.getStatus());
            }
        }
    }
}

// Удаление всех задач
public void deleteAllTasks() {
    for (Task task : tasks.values()) {
        if (task instanceof Epic) {
            ((Epic) task).clearAllSubTasksIds();
        }
        tasks.remove(task);
    }
}

// удаление задачи по индексу
public void removeTask(int index) {
    Task task = tasks.get(index);

    if (task == null) {
        System.out.println("Задача с id: " + index + " не найдена");
    }

    if (task instanceof Epic) {
        Epic epic = (Epic) task;

        for (Integer subTaskId : epic.getSubTasksIds()) {
            tasks.remove(subTaskId);
            System.out.println("Удалена подзадача c id = " + subTaskId);
        }
    }

    tasks.remove(index);
    //System.out.println("Удалена задача c id = " + index);

    if (task instanceof SubTask) {
        int epicIndex = ((SubTask) task).getEpikIndex();
        Epic epic = (Epic) tasks.get(epicIndex);
        epic.clearSubTasksIds(index);

        if (epic.getSubTaskCount() == 0) {
            epic.status = Task.Status.NEW;
            updateTask(epicIndex, epic);
        }
        //else {
//                recalculateEpicStatus(epicIndex);
//            }
    }


}

    }
