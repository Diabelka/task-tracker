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
        subTaskSecond.setTitle("Тестовая подзадача 2");
        subTaskSecond.setDescription("Описание подзадачи 2");
        subTaskSecond.setStatus(Status.NEW);
        subTaskSecond.setEpicIndex(epic.getId());
        manager.addTask(subTaskSecond);

        SubTask subTask3 = new SubTask();
        subTask3.setTitle("Тестовая подзадача 3");
        subTask3.setDescription("Описание подзадачи 3");
        subTask3.setStatus(Status.NEW);
        subTask3.setEpicIndex(epic.getId());
        manager.addTask(subTask3);

        Task task2 = new Task();
        task2.setTitle("Тестовая задача 2");
        task2.setDescription("Описание тестовой задачи 2");
        task2.setStatus(Status.NEW);
        manager.addTask(task2);

        Task task3 = new Task();
        task3.setTitle("Тестовая задача 3");
        task3.setDescription("Описание тестовой задачи 3");
        task3.setStatus(Status.NEW);
        manager.addTask(task3);

        Task task4 = new Task();
        task4.setTitle("Тестовая задача 4");
        task4.setDescription("Описание тестовой задачи 4");
        task4.setStatus(Status.NEW);
        manager.addTask(task4);

        Task task5 = new Task();
        task5.setTitle("Тестовая задача 5");
        task5.setDescription("Описание тестовой задачи 5");
        task5.setStatus(Status.NEW);
        manager.addTask(task5);

        Task task6 = new Task();
        task6.setTitle("Тестовая задача 6");
        task6.setDescription("Описание тестовой задачи 6");
        task6.setStatus(Status.NEW);
        manager.addTask(task6);

        Task task7 = new Task();
        task7.setTitle("Тестовая задача 7");
        task7.setDescription("Описание тестовой задачи 7");
        task7.setStatus(Status.NEW);
        manager.addTask(task7);

        Task task8 = new Task();
        task8.setTitle("Тестовая задача 8");
        task8.setDescription("Описание тестовой задачи 8");
        task8.setStatus(Status.NEW);
        manager.addTask(task8);

        Task task9 = new Task();
        task9.setTitle("Тестовая задача 9");
        task9.setDescription("Описание тестовой задачи 9");
        task9.setStatus(Status.NEW);
        manager.addTask(task9);

        return manager;
    }
}

