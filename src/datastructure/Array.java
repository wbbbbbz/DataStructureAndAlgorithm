package datastructure;

import java.security.spec.ECFieldFp;
import java.util.ArrayList;

/**
 * 二次封装java的数组类，使原有的静态数组封装为动态数组 需要支持增删改查(CRUD) 使用capacity代表一开始数组的大小
 * 使用size代表实际拥有的数据多少（等于第一个空的索引） 支持的操作：增（头，尾，特定位置），删，改（特定位置），查（查询索引），保持大小变量，
 * 打印输出，是否包含，查找一个，查找全部，增删是静态还是动态
 */
public class Array<E> {
    /**
     * 数组
     */
    private E[] data;

    /**
     * 数组数据多少
     */
    private int size;

    /**
     * @param capacity 是该数组的初始大小
     */
    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(E[] data) {
        this.data = data;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                this.size = i;
            }
        }
        this.size = data.length;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 往数组末尾添加元素
    public void addLast(E e) {
        // // 情况1. 数组未满
        // if (size < data.length) {
        // data[size] = number;
        // size++;
        // }
        // // 情况2. 数组已满（扩容）
        // // else {
        // // int[] tempIntArray = new int[2 * this.data.length];
        // // for (int i = 0; i < data.length; i++) {
        // // tempIntArray[i] = data[i];
        // // }
        // // tempIntArray[data.length] = number;
        // // }
        // // 情况2. 数组已满(错误)
        // throw new IllegalArgumentException("AddLast failed. Array is full.");
        this.insert(size, e);
    }

    // 往数组头添加元素
    public void addFirst(E e) {
        this.insert(0, e);
    }

    // 往数组指定位置插入元素
    public void insert(int index, E e) {
        // 情况1. index超出数组范围
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Insert failed. Index is out of bound at " + index + ".");
        }
        // // 情况2. 数据已满
        // if (size == data.length) {
        // throw new IllegalArgumentException("Insert failed. Array is full.");
        // }
        if (size == data.length) {
            resize(2 * data.length);
        }
        // 情况3. 插入
        for (int i = size - 1; i >= index; i--) {
            this.data[i + 1] = this.data[i];
        }
        this.data[index] = e;
        size++;
    }

    // 取出某一个元素
    public E get(int index) {
        // 情况1. index超出数组范围
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is out of bound.");
        }
        return data[index];
    }

    public void set(int index, E e) {
        // 情况1. index超出数组范围
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is out of bound.");
        }
        data[index] = e;
    }

    // 查找是否包含要素
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    // 返回具体索引
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // 删除数组中指定索引元素
    public E remove(int index) {
        // 情况1. index超出数组范围
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Delete failed. Index is out of bound.");
        }
        Object output = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;

        // 防止复杂度震荡的Lazy缩容策略
        if (size < data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        data[size] = null; // loitering objects != memory leak
        return (E) output;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // 只删除一个元素，并且返回删除成功与否
    public boolean removeElement(E e) {
        // for (int i = 0; i < size; i++) {
        // if (data[i] == number) {
        // for (int j = i; j < size - 1; j++) {
        // data[j] = data[j + 1];
        // }
        // size--;
        // break;
        // }
        // }
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    // 调整数组大小
    private void resize(int capacity){
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        output.append("[");
        for (int i = 0; i < size; i++) {
            output.append(data[i]);
            if (i != size - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        return output.toString();
    }
}