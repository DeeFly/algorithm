package info.gaofei.algorithm.list;

/**
 * Created by GaoQingming on 2018/10/1.
 */
public class MyLinkedList {
    private int count;
    private Node<Integer> head;
    private Node<Integer> tail;


    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(1, 2);
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(2));
    }

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        Node<Integer> node = getNode(index);
        if (node == null) {
            return -1;
        } else {
            return node.value;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Node result;
        if (index < size() / 2) {
            result = head.next;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            index = count - 1 - index;
            result = tail.pre;
            for (int i = 0; i < index; i++) {
                result = result.pre;
            }
        }

        return result;
    }

    private int size() {
        return this.count;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node<Integer> node = new Node<>(val);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node<Integer> node = new Node<>(val);
        node.next = tail;
        node.pre = tail.pre;
        node.pre.next = node;
        node.next.pre = node;
        count++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        Node newNode = new Node(val);
        if (index == 0) {
            addAtHead(val);
        } else if (index == size()) {
            addAtTail(val);
        } else {
            Node node = getNode(index);
            if (node == null) {
                return;
            }
            newNode.next = node;
            newNode.pre = node.pre;
            newNode.next.pre = newNode;
            newNode.pre.next = newNode;
            count++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        Node node = getNode(index);
        if (node == null) {
            return;
        }

        node.pre.next = node.next;
        node.next.pre = node.pre;
        count--;
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    class Node<T> {
        Node(T value) {
            this.value = value;
        }

        T value;
        Node next;
        Node pre;
    }
}
