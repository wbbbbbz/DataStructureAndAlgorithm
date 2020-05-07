package datastructure;

import java.util.Arrays;

public class IndexMaxHeap<E extends Comparable<E>> {

    private Array<E> data;
    private int[] indexes;
    private int[] reverse;

    public IndexMaxHeap(int capacity) {
        data = new Array<>(capacity);
        indexes = new int[capacity];
        reverse = new int[capacity];
        for (int i = 0; i < reverse.length; i++) {
            reverse[i] = -1;
            indexes[i] = -1;
        }
    }

    // heapify
    public IndexMaxHeap(E[] array) {
        data = new Array<>(array);
        indexes = new int[array.length];
        reverse = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reverse[i] = i;
            indexes[i] = i;
        }
        for (int i = getParent(getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public IndexMaxHeap() {
        this(10);
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
    private int getMaxChild(int index) {
        if (!hasChild(index))
            throw new IllegalArgumentException("index-" + index + " doesn't have child.");
        if (index * 2 + 2 >= getSize())
            return getLeftChild(index);
        return data.get(indexes[getLeftChild(index)]).compareTo(data.get(indexes[getRightChild(index)])) > 0
                ? getLeftChild(index)
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
        int i = data.getSize() - 1;
        indexes[i] = i;
        reverse[i] = i;
        siftUp(data.getSize() - 1);
    }

    // 替换堆中最大元素
    public E replace(E e) {
        E res = getMax();
        data.set(indexes[0], e);
        siftDown(0);
        return res;
    }

    // 上浮过程
    private void siftUp(int index) {
        while (index > 0 && data.get(indexes[index]).compareTo(data.get(indexes[getParent(index)])) > 0) {
            swapIndexes(index, getParent(index));
            index = getParent(index);
        }
    }

    // 看堆中最大值
    public E getMax() {
        judgeEmpty();
        return data.get(indexes[0]);
    }

    // 看堆中最大值的索引
    public int getMaxIndex() {
        judgeEmpty();
        return indexes[0];
    }

    private void judgeEmpty() {
        if (data.isEmpty())
            throw new IllegalArgumentException("Empty maxHeap!");
    }

    // 从堆中取出最大值
    public E extractMax() {
        judgeEmpty();
        E res = data.get(indexes[0]);
        swapIndexes(0, getSize() - 1);
        // indexes[getSize() - 1] = -1;
        reverse[getSize() - 1] = -1;
        // data.remove(indexes[getSize() - 1]);
        siftDown(indexes[0]);
        return res;
    }

    // 从堆中取出最大值对应的索引
    public int extractMaxIndex() {
        judgeEmpty();
        int res = indexes[0];
        swapIndexes(0, getSize() - 1);
        // indexes[getSize() - 1] = -1;
        reverse[getSize() - 1] = -1;
        // data.remove(indexes[getSize() - 1]);
        siftDown(indexes[0]);
        return res;
    }

    // 下沉过程
    private void siftDown(int index) {
        while (hasChild(index) && data.get(indexes[getMaxChild(index)]).compareTo(data.get(indexes[index])) > 0) {
            swapIndexes(index, getMaxChild(index));
            index = getMaxChild(index);
        }
    }

    protected E get(int index) {
        assert (contain(index));
        return data.get(index);
    }

    private void swapIndexes(int i, int j) {
        int temp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = temp;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;

    }

    public void set(int i, E e) {
        assert (contain(i));

        data.set(i, e);

        // 必须找到indexes[j] = i
        int index = reverse[i];
        siftUp(index);
        siftDown(index);
        return;

    }

    public boolean contain(int i) {
        assert (i >= 0 && i < getSize());
        return reverse[i] != -1;
    }

    // 测试索引堆中的索引数组index和反向数组reverse
    // 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes() {

        int[] copyIndexes = new int[getSize()];
        int[] copyReverseIndexes = new int[getSize()];

        for (int i = 0; i <= getSize(); i++) {
            copyIndexes[i] = indexes[i];
            copyReverseIndexes[i] = reverse[i];
        }

        copyIndexes[0] = 0;
        copyReverseIndexes[0] = 0;
        Arrays.sort(copyIndexes);
        Arrays.sort(copyReverseIndexes);

        // 在对索引堆中的索引和反向索引进行排序后,
        // 两个数组都应该正好是1...count这count个索引
        boolean res = true;
        for (int i = 1; i <= getSize(); i++)
            if (copyIndexes[i - 1] + 1 != copyIndexes[i] || copyReverseIndexes[i - 1] + 1 != copyReverseIndexes[i]) {
                res = false;
                break;
            }

        if (!res) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }

}