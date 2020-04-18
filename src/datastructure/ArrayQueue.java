package datastructure;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> data;

    public ArrayQueue(int capacity) {
        data = new Array(capacity);
    }

    public ArrayQueue() {
        data = new Array();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
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
        output.append(String.format("Queue: size = %d, capacity = %d\n", getSize(), data.getCapacity()));
        output.append("Front [");
        for (int i = 0; i < getSize(); i++) {
            output.append(data.get(i));
            if (i != getSize() - 1) {
                output.append(", ");
            }
        }
        output.append("] tail");
        return output.toString();
    }

}