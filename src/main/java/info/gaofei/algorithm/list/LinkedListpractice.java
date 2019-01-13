package info.gaofei.algorithm.list;

/**
 * Created by GaoQingming on 2019/1/9.
 */
public class LinkedListpractice<T extends Comparable> {
    private int count;
    private Node<T> header;
    private Node<T> tail;

    public LinkedListpractice() {
        header = new Node<>(null);
        tail = new Node<>(null);
        header.next = tail;
        tail.pre = header;
    }

    public static void main(String[] args) {
        LinkedListpractice<Integer> linkedListpractice = new LinkedListpractice<>();
        linkedListpractice.add(2);
        linkedListpractice.add(1);
        linkedListpractice.add(3);
        linkedListpractice.addAt(100, 0);
        linkedListpractice.removeAt(0);
        System.out.println(linkedListpractice.toString());
    }

    public void add(T value) {
        addAtTail(value);
    }

    public void addAt(T value, int index) {
        if (index == size()) {
            add(value);
            return;
        }

        Node node = getNode(index);
        addBeforeNode(value, node);
    }

    private void addBeforeNode(T value, Node<T> node) {
        Node newNode = new Node(value);
        newNode.pre = node.pre;
        node.pre.next = newNode;
        newNode.next = node;
        node.pre = newNode;
        count++;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    public T removeAt(int index) {
        Node<T> node = getNode(index);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        count--;
        return node.value;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node node;
        if (index <= size() / 2) {
            node = header;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            index = size() - 1 - index;
            for (int i = 0; i <= index; i++) {
                node = node.pre;
            }
        }
        return node;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Node node = header.next;
        stringBuilder.append("[");
        while (node.next != tail) {
            stringBuilder.append(node.value).append(",");
            node = node.next;
        }
        stringBuilder.append(node.value).append("]");
        return stringBuilder.toString();
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    private void addAtTail(T value) {
        Node newNode = new Node(value);
        newNode.pre = tail.pre;
        tail.pre.next = newNode;
        newNode.next = tail;
        tail.pre = newNode;
        count++;
    }

    private class Node<T> {
        private T value;
        private Node next;
        private Node pre;
        public Node(T value) {
            this.value = value;
        }
    }
}
