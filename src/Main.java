import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //TaskManager manager = TaskMamagers.getDefault();
        TaskManager manager = TaskMamagers.withTestData();
        //HistoryManager history = TaskMamagers.getDefaultHistory();
        int maxUserInput = 6;
        System.out.println("Добро пожаловать в трекер задач!");

        printMenu();
        int userInput = scanner.nextInt();
        scanner.nextLine();

        while (userInput != maxUserInput) {
            if (userInput <= 0 || userInput > maxUserInput)
                printError();

            switch (userInput) {
                case 1: // Добавляем задачи
                    System.out.println("Введите название задачи ");
                    String title = scanner.nextLine();
                    System.out.println("Введите описание здачи ");
                    String description = scanner.nextLine();

                    System.out.println("Чтобы добавить подзадачу введите 1 ");
                    System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                    int newSubTask = scanner.nextInt();
                    scanner.nextLine();

                    if (newSubTask == 1) {
                        Epic epic = new Epic();
                        epic.setTitle(title);
                        epic.setDescription(description);
                        epic.setStatus(Status.NEW);
                        manager.addTask(epic);

                        while (newSubTask == 1) {

                            System.out.println("Введите название подзадачи ");
                            String subTitle = scanner.nextLine();
                            System.out.println("Введите описание подздачи ");
                            String subDescription = scanner.nextLine();

                            SubTask subTask = new SubTask();
                            subTask.setTitle(subTitle);
                            subTask.setDescription(subDescription);
                            subTask.setEpicIndex(epic.getId());
                            subTask.setStatus(Status.NEW);
                            manager.addTask(subTask);

                            System.out.println("Чтобы добавить подзадачу введите 1 ");
                            System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                            newSubTask = scanner.nextInt();
                            scanner.nextLine();
                        }
                    } else {
                        Task task = new Task();
                        task.setTitle(title);
                        task.setDescription(description);
                        task.setStatus(Status.NEW);
                        manager.addTask(task);
                    }

                    break;
                case 2: // Просмотр списка задач
                    System.out.println("1 - посмотреть список всех задач");
                    System.out.println("2 - найти задачу по id");

                    int printTask = scanner.nextInt();
                    scanner.nextLine();

                    if (printTask == 1) {
                        TaskPrinter.printAllTasks(manager.getAllTasks());
                    } else if (printTask == 2) {
                        System.out.println("введите id задачи");
                        printTask = scanner.nextInt();
                        Task task = new Task();
                        task = manager.getTask(printTask);

                        if (task == null) {
                            break;
                        }

                        TaskPrinter.printTask(task);

                        if (task instanceof Epic) {
                            System.out.println("Для вывода подзалач нажмите 1");
                            int subTask = scanner.nextInt();
                            scanner.nextLine();
                            if (subTask == 1) {
                                TaskPrinter.printSubTasks(manager.getAllSubTasks(printTask));
                            }
                        }
                        printTask = 0;
                    }
                    break;
                case 3: //Изменить статус задачи
                    System.out.println("Введите id задачи для изменения её ституса");
                    int indexTask = scanner.nextInt();
                    scanner.nextLine();

                    Task task = manager.getTask(indexTask);

                    if (task instanceof Epic) {
                        System.out.println("Задача с id: " + indexTask + " пренадлежит классу эпик. " +
                                "Её статус завист от статуча подзадач");
                    } else {
                        System.out.println("Задача с id: " + indexTask + " имеет статус " + task.getStatus());
                        System.out.println("Выберете новый статус задачи:");
                        System.out.println("1 - NEW");
                        System.out.println("2 - IN_PROGRESS");
                        System.out.println("3 - DONE");

                        int newStatus = scanner.nextInt();
                        scanner.nextLine();

                        switch (newStatus) {
                            case 1:
                                if (Status.NEW.equals(task.getStatus())) {
                                    System.out.println("Новый и старый статус совпадают");
                                } else {
                                    task.setStatus(Status.NEW);
                                }
                                break;
                            case 2:
                                if (Status.IN_PROGRESS.equals(task.getStatus())) {
                                    System.out.println("Новый и старый статус совпадают");
                                } else {
                                    task.setStatus(Status.IN_PROGRESS);
                                }
                                break;
                            case 3:
                                if (Status.DONE.equals(task.getStatus())) {
                                    System.out.println("Новый и старый статус совпадают");
                                } else {
                                    task.setStatus(Status.DONE);
                                }
                                break;
                        }

                        manager.updateTask(indexTask, task);
                    }

                    break;
                case 4: // Удаление задачи
                    System.out.println("Ведите идентификационный номер для задачи которую хотите удалить: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeTask(index);
                    break;
                case 5:
                    ArrayList <Task> historyTasks = manager.getHistoryTasks();
                    TaskPrinter.printAllTasks(historyTasks);
                default:
                    break;
            }
            printMenu();
            userInput = scanner.nextInt();
            scanner.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("Выберите действие и введите цифру:");
        System.out.println("1 - добавить задачу");
        System.out.println("2 - посмотреть список задач");
        System.out.println("3 - изменить статус задачи");
        System.out.println("4 - удалить задачу");
        System.out.println("5 - история просмотра задач");
        System.out.println("6 - выход");
    }

    private static void printError() {
        System.out.println("Ошибка, такой команды нет. Попробуйте ещё раз!");
    }

}