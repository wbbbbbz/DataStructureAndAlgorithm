package datastructure;

// 用链表头模拟栈，所以栈顶是first, 栈底是last
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> data;

    public LinkedListStack() {
        this.data = new LinkedList<>();
    }

    // 入栈进入链表头
    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    // 出栈出链表头
    @Override
    public E pop() {
        return data.removeFirst();
    }

    // 看链表头
    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("LinkedListStack: size = %d\n", getSize()));
        output.append("Top");
        output.append(data.toString());
        return output.toString();
    }

}