import java.util.ArrayList;

public class TaskPrinter {

    public static void printAllTasks(ArrayList<Task> tasks) {

        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список задач === ");

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

        if (task == null) {
            System.out.println("Задача не найдена");
        } else {
            if (task instanceof Epic) {
                System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() + " относится к классу Epic ");
            } else if (task instanceof SubTask) {
                int epicIndex = ((SubTask) task).getEpicIndex();
                System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() +
                        " является подзадачей эпика c id:" + epicIndex);
            } else {
                System.out.println("Задача с id:" + task.getId() + " - " + task.getTitle() + "-" + task.getStatus());
            }
        }

    }

    public static void printSubTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" Список задач пуст");
        } else {
            System.out.println("=== Список подзадач === ");

            for (Task task : tasks) {
                System.out.println("Подзадача с id:" + task.getId() + " - " + task.getTitle() + " - " +
                        task.getDescription() + " - " + task.getStatus());
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
                if (task instanceof SubTask) {
                    epicIndex = ((SubTask) task).getEpicIndex();
                    System.out.println(++currentIndex + ". Подзадача с id: " + task.getId() + " -  Эпика с id: " +
                            epicIndex + " - " + task.getTitle() + " - " + task.getDescription() +
                            " - " + task.getStatus());
                } else {
                    System.out.println(++currentIndex + ". Задача с id:" + task.getId() + " - " + task.getTitle() +
                            " - " + task.getDescription() + " - " + task.getStatus());
                }
            }
            System.out.println("========================= ");
        }
    }

}
