package datastructure;

public interface Stack<E> {

    // 入栈
    void push(E e);

    // 出栈
    E pop();

    // 查看栈顶
    E peek();

    // 查看数据个数
    int getSize();

    // 判断是否为空
    boolean isEmpty();



}