import java.util.ArrayList;

public class TaskPrinter {

    public static void printAllTasks(ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список задач === ");
            System.out.println("Задачи: ");

            for (Task task : tasks) {
                if (!(task instanceof SubTask)) {
                    System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() + " - " +
                            task.getDescription() + " - " + task.getStatus());
                }
            }
            System.out.println("========================= ");
        }
    }

    public static void printTask(Task task) {

        if (task instanceof Epic) {
            System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() + " относится к классу Epic ");
        } else if (task instanceof SubTask) {
            int epicIndex = ((SubTask) task).getEpikIndex();
            System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() +
                    " является подзадачей эпика c id:" + epicIndex);
        } else {
            System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() + "-" + task.getStatus());
        }
    }

    public static void printSubTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список подзадач === ");
            System.out.println("Подзадачи: ");

            for (Task task : tasks) {
                System.out.println("Подзадача с id:" + task.getId() + " - " + task.getTitle() + " - " +
                        task.getDescription() + " - " + task.getStatus());
            }
            System.out.println("========================= ");
        }
    }


}
