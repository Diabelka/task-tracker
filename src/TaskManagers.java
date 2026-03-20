public final class TaskManagers {

    private TaskManagers() {                 // приватный конструктор
        throw new AssertionError("Служебный класс не может быть создан");
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager withTestData() {
        TaskManager manager = new InMemoryTaskManager();

        Task task = new Task();
        task.setTitle("Тестовая задача");
        task.setDescription("Описание тестовой задачи");
        task.setStatus(Status.NEW);
        manager.addTask(task);

        Epic epic = new Epic();
        epic.setTitle("Тестовый эпик");
        epic.setDescription("Описание эпика");
        epic.setStatus(Status.NEW);
        manager.addTask(epic);

        SubTask subTaskFirst = new SubTask();
        subTaskFirst.setTitle("Тестовая подзадача");
        subTaskFirst.setDescription("Описание подзадачи");
        subTaskFirst.setStatus(Status.NEW);
        subTaskFirst.setEpicIndex(epic.getId());
        manager.addTask(subTaskFirst);

        SubTask subTaskSecond = new SubTask();
        subTaskSecond.setTitle("Тестовая подзадача");
        subTaskSecond.setDescription("Описание подзадачи");
        subTaskSecond.setStatus(Status.NEW);
        subTaskSecond.setEpicIndex(epic.getId());
        manager.addTask(subTaskSecond);

        return manager;
    }
}

