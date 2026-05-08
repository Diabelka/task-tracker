import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class InMemoryHistoryManager implements HistoryManager {

    static class Node{
        Task value;
        Node next;
        Node prev;

        public Node(Task value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node first;
    private Node last;
    private int size =  0;

    private static final int MAX_HISTORY_SIZE = 10;
    private final HashMap<Integer, Node> historyMap = new HashMap<>();

    private void removeNode(Node node){
        if (node == null){
            return;
        }

        if(size == 1){
            first = null;
            last = null;
            size--;
            return;
        }

        Node prev = node.prev;
        Node next = node.next;

        if(prev == null){
            next.prev = null;
            first = next;
            size--;
            return;
        }

        if(next == null){
            last = prev;
            prev.next = null;
            size--;
            return;
        }

        prev.next = next;
        next.prev = prev;
        size--;

        node.prev = null;
        node.next = null;
        node.value = null;
    }

    private List<Task> getTasks() {
        ArrayList<Task> historyList = new ArrayList<>();
        Node current = this.first;

        if (current == null){
            return new ArrayList<>();
        }
        while(current != null){
            historyList.add(current.value);
            current = current.next;
        }

        return historyList;
    }

    private void linkLast(Task value){
        final Node newNode = new Node(value, null, null);

        if (size == 0){
            size++;
            first = newNode;
            last = newNode;
            return;
        }

        size++;
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
    }

    @Override
    public void add(Task task) {

        if (task == null) {
            return;
        }

        if (historyMap.containsKey(task.getId()))
        {
            removeNode(historyMap.get(task.getId()));
        }

        linkLast(task);
        historyMap.put(task.getId(), last);

        if (size >= MAX_HISTORY_SIZE){
            remove(first.value.getId());
        }
    }

    @Override
    public void remove(int indexId) {
        if (!(historyMap.containsKey(indexId)))
        {
            return;
        }

        removeNode(historyMap.get(indexId));
        historyMap.remove(indexId);
    }

    @Override
    public List <Task> getHistory() {
        return new ArrayList<>(getTasks());
    }
}
