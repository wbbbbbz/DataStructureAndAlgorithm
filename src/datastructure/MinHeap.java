package datastructure;

public class MinHeap<E extends Comparable> {

    private Array<E> data;

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    // heapify
    public MinHeap(E[] array) {
        data = new Array<>(array);
        for (int i = getParent(getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public MinHeap() {
        data = new Array<>();
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int getParent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");

        return (index - 1) / 2;
    }

    // 判断有无子节点
    private boolean hasChild(int index) {
        return index * 2 + 1 < getSize();
    }

    // 返回最大子节点的索引
    private int getMinChild(int index) {
        if (!hasChild(index))
            throw new IllegalArgumentException("index-" + index + " doesn't have child.");
        if (index * 2 + 2 >= getSize())
            return getLeftChild(index);
        return data.get(getLeftChild(index)).compareTo(data.get(getRightChild(index))) < 0 ? getLeftChild(index)
                : getRightChild(index);
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int getLeftChild(int index) {
        if (index * 2 + 1 >= getSize())
            throw new IllegalArgumentException("index-" + index + " doesn't have leftChild.");

        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int getRightChild(int index) {
        if (index * 2 + 2 >= getSize())
            throw new IllegalArgumentException("index-" + index + " doesn't have rightChild.");

        return index * 2 + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    // 替换堆中最大元素
    public E replace(E e) {
        E res = findMin();
        data.set(0, e);
        siftDown(0);
        return res;
    }

    // 上浮过程
    private void siftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(getParent(index))) < 0) {
            data.swap(index, getParent(index));
            index = getParent(index);
        }
    }

    // 看堆中最大值
    public E findMin() {
        judgeEmpty();
        return data.get(0);
    }

    private void judgeEmpty() {
        if (data.isEmpty())
            throw new IllegalArgumentException("Empty maxHeap!");
    }

    // 从堆中取出最大值
    public E extractMin() {
        judgeEmpty();
        E res = data.get(0);
        data.swap(0, getSize() - 1);
        data.removeLast();
        siftDown(0);
        return res;
    }

    // 下沉过程
    private void siftDown(int index) {
        while (hasChild(index) && data.get(getMinChild(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, getMinChild(index));
            index = getMinChild(index);
        }
    }

    protected E get(int index) {
        return data.get(index);
    }

    @Override
    public String toString() {
        return data.toString();
    }

}