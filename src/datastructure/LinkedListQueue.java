package datastructure;

// 以链表实现队列。链表头head负责出队，也就是head = front
// 所以tail = last
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        Node node = new Node(e);
        if (isEmpty()) {
            head = node;
            tail = node;
            tail.next = null;
        } else {
            tail.next = node;
            tail = tail.next; // 注意tail节点的维护！
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("LinkedListQueue is Empty!");
        Node node = head;
        head = head.next;
        node.next = null;
        size--;
        if (isEmpty())
            tail = null;
        return node.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("LinkedListQueue is Empty!");
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("LinkedListQueue: size = %d\n", getSize()));
        output.append("Queue: Front: ");
        Node node = head;
        while (node != null) {
            output.append(node.e.toString() + " -> ");
            node = node.next;
        }
        output.append("NULL Tail");
        return output.toString();
    }
}