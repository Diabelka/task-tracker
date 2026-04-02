import java.util.ArrayList;

public class TaskPrinter {

    public static void printAllTasks(ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список задач === ");

            for (Task task : tasks) {
                if (!(task instanceof SubTask)) {
                    System.out.println(task);
                }
            }
            System.out.println("========================= ");
        }
    }

    public static void printTask(Task task) {

        if (task == null) {
            System.out.println("Задача не найдена");
        } else {

            System.out.println(task);
        }

    }

    public static void printSubTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список подзадач === ");

            for (Task task : tasks) {
                System.out.println(task);
            }
            System.out.println("========================= ");
        }
    }

    public static void printHistory (ArrayList<Task> tasks) {

        int currentIndex = 0;
        int epicIndex;

        if (tasks.isEmpty()) {
            System.out.println(" История просмотров пуста");
        } else {
            System.out.println("=== История просмотров: === ");
            for (Task task : tasks) {
                System.out.println(++currentIndex + task.toString());
            }
            System.out.println("========================= ");
        }
    }

}
