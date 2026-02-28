import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Meneger meneger = new Meneger();
        System.out.println("Добро пожаловать трекер задач!");

        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 5) {
            if (userInput <= 0 || userInput > 5)
                printError();

            switch (userInput) {
                case 1: // Добавляем задачи
                    System.out.println("Введите название задачи ");
                    String title = scanner.next();
                    System.out.println("Введите описание здачи ");
                    String description = scanner.next();

                    System.out.println("Чтобы добавить подзадачу введите 1 ");
                    System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                    int subTask = scanner.nextInt();

                    if (subTask == 1) {
                        Epic epic = new Epic();
                        epic.setTitle(title);
                        epic.setDescription(description);

                        while (subTask == 1) {

                            System.out.println("Введите название подзадачи ");
                            String subTitle = scanner.next();
                            System.out.println("Введите описание подздачи ");
                            String subDescription = scanner.next();

                            System.out.println("Чтобы добавить подзадачу введите 1 ");
                            System.out.println("Чтобы вернуться в главное меню введите любое другое число ");
                            subTask = scanner.nextInt();
                        }
                        meneger.addTask(epic);
                    }
                    else {
                        Task task = new Task();
                        task.setTitle(title);
                        task.setDescription(description);
                        meneger.addTask(task);
                    }

                    break;
                case 2: // Просмотр списка задач

                    break; //
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
            printMenu();
            userInput = scanner.nextInt();
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