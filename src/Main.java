import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Meneger meneger = new Meneger();
        System.out.println("Добро пожаловать трекер задач!");

        printMenu();
        int userInput = scanner.nextInt();
        scanner.nextLine();

        while (userInput != 5) {
            if (userInput <= 0 || userInput > 5)
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
                        //epic.setStatus(Task.Status.NEW);
                        meneger.addTask(epic);

                        while (newSubTask == 1) {

                            System.out.println("Введите название подзадачи ");
                            String subTitle = scanner.nextLine();
                            System.out.println("Введите описание подздачи ");
                            String subDescription = scanner.nextLine();

                            SubTask subTask = new SubTask();
                            subTask.setTitle(subTitle);
                            subTask.setDescription(subDescription);
                            subTask.setEpikIndex(epic.getId());
                            subTask.setStatus(Task.Status.NEW);
                            meneger.addTask(subTask);

                            System.out.println("Чтобы добавить подзадачу введите 1 ");
                            System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                            newSubTask = scanner.nextInt();
                            scanner.nextLine();
                        }
                       // meneger.addTask(epic);
                    }
                    else {
                        Task task = new Task();
                        task.setTitle(title);
                        task.setDescription(description);
                        task.setStatus(Task.Status.NEW);
                        meneger.addTask(task);
                    }

                    break;
                case 2: // Просмотр списка задач
                    System.out.println("1 - посмотреть список всех задач");
                    System.out.println("2 - найти задачу по id");

                    int printTask = scanner.nextInt();
                    scanner.nextLine();

                    if (printTask == 1) {
                        meneger.printAllTasks();
                    } else if (printTask == 2) {
                        System.out.println("введите id задачи");
                        printTask = scanner.nextInt();

                        meneger.printTasks(printTask);

                        Task task = new Task();
                        task = meneger.getTask(printTask);
                        if (task instanceof Epic)
                        {
                            System.out.println("Для вывода подзалач нажмите 1");
                            int subTask = scanner.nextInt();
                            scanner.nextLine();
                            if (subTask == 1) {
                                meneger.printSubTasks(printTask);
                            }
                        }
                        printTask = 0;
                    }
                    break; //Изменить статус задачи
                case 3:
                    break;
                case 4: // Удаление задачи
                    System.out.println("Ведите идентификационный номер для задачи которую хотите удалить: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    meneger.removeTask(index);

                    break;
                default:
                    break;
            }
            printMenu();
            userInput = scanner.nextInt();
            scanner.nextLine();
        }
    }

//    private enum PrintTask {
//        EPIC,
//        TASK
//    }

    private static void printMenu() {
        System.out.println("Выберите действие и введите цифру:");
        System.out.println("1 - добавить задачу");
        System.out.println("2 - посмотреть список задач");
        System.out.println("3 - изменить статус задачи");
        System.out.println("4 - удалить задачу");
        System.out.println("5 - выход");
    }

    private static void printError() {
        System.out.println("Ошибка, такой команды нет. Попробуйте ещё раз!");
    }
}