package datastructure;

public interface Queue<E> {

    // 入队
    void enqueue(E e);

    // 出队
    E dequeue();

    // 看队首
    E getFront();

    // 看队列大小
    int getSize();

    // 看队列是否为空
    boolean isEmpty();

}