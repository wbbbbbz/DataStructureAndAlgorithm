package datastructure;

public class LinkedList<E> {

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

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // 获取元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表的特定位置中添加新的元素e
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node prev = dummyHead;
        // while (true){
        // if (index == 0){
        // prev.next = new Node(e, prev.next);
        // return;
        // }
        // index --;
        // prev = prev.next;
        // }
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 获得链表第index个位置的元素（0-based）
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node node = dummyHead.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.e;
    }

    // 获得第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获得最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 更新第index个位置的元素(0-based)
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node node = dummyHead.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node node = dummyHead.next;
        // for (int i = 0; i < size; i++) {
        // if (node.e == e)
        // return true;
        // node = node.next;
        // }
        // return false;
        while (node != null) {
            if (node.e == e)
                return true;
            node = node.next;
        }
        return false;
    }

    // 删除链表第index个位置的元素（0-based）
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        // 方便垃圾回收！因为删除的节点存在一个引用，所以无法被回收！
        retNode.next = null;
        size--;
        return retNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("LinkedList: size = %d\n", getSize()));
        output.append("Head: ");
        Node node = dummyHead.next;
        while (node != null) {
            output.append(node.e.toString() + " -> ");
            node = node.next;
        }
        output.append("NULL");
        return output.toString();
    }
}