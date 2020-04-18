package datastructure;

public class ArrayStack<E> implements Stack<E> {

    // 底层是通过成员变量进行实现
    private Array<E> data;

    // 用户不应该可以看见栈中间的要素！

    public ArrayStack(int capacity) {
        data = new Array<E>(capacity);
    }

    public ArrayStack() {
        data = new Array<E>();
    }

    public int getCapacity() {
        return data.getCapacity();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
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
        output.append(String.format("Stack: size = %d, capacity = %d\n", getSize(), data.getCapacity()));
        output.append("[");
        for (int i = 0; i < getSize(); i++) {
            output.append(data.get(i));
            if (i != getSize() - 1) {
                output.append(", ");
            }
        }
        output.append("] top");
        return output.toString();
    }

}