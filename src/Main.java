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

                        while (newSubTask == 1) {

                            System.out.println("Введите название подзадачи ");
                            String subTitle = scanner.nextLine();
                            System.out.println("Введите описание подздачи ");
                            String subDescription = scanner.nextLine();

                            SubTask subTask = new SubTask();
                            subTask.setTitle(subTitle);
                            subTask.setDescription(subDescription);
                            epic.addSubTask(subTask);

                            System.out.println("Чтобы добавить подзадачу введите 1 ");
                            System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                            newSubTask = scanner.nextInt();
                            scanner.nextLine();
                        }
                        meneger.addTask(epic);
                    }
                    else {
                        Task task = new Task();
                        task.setTitle(title);
                        task.setDescription(description);
                        task.setStatus("NEW");
                        meneger.addTask(task);
                    }

                    break;
                case 2: // Просмотр списка задач
                    meneger.printAllTasks();
                    break; //
                case 3:
                    break;
                case 4: // Удаление задачи
                    System.out.println("Ведите идентификационный номер для задачи которую хотите удалить: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    meneger.deleteTask(index);

                    break;
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
        System.out.println("5 - выход");
    }

    private static void printError() {
        System.out.println("Ошибка, такой команды нет. Попробуйте ещё раз!");
    }
}