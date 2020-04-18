package datastructure;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    // 队首指针
    private int front, tail;
    // 队尾指针
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("LoopQueue is empty!");
        }
        E temp = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size <= getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return temp;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("LoopQueue is empty!");
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        this.data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("LoopQueue: size = %d, capacity = %d\n", size, getCapacity()));
        output.append("Front [");
        for (int i = 0; i < size; i++) {
            output.append(data[(front + i) % data.length]);
            if (i != size - 1) {
                output.append(", ");
            }
        }
        output.append("] tail");
        return output.toString();
    }
}