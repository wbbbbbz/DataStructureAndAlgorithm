package algorithm.sorting;

import datastructure.MaxHeap;

public class HeapSort {

    // 堆排序HeapSort
    public static void sort(Comparable[] arr) {
        heapSort2(arr);
    }

    private static void heapSort1(Comparable[] arr) {
        Integer[] temparr = new Integer[arr.length];
        for (int i = 0; i < temparr.length; i++) {
            temparr[i] = (Integer) arr[i];
        }
        MaxHeap<Integer> maxHeap = new MaxHeap<>(temparr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    private static void heapSort2(Comparable[] arr) {
        for (int i = getParent(arr.length - 1); i >= 0; i--)
            siftDown(arr, i);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            siftDown(arr, 0);
        }
    }

    private static void siftDown(Comparable[] arr, int i) {
        while (hasChild(arr, i) && arr[getMaxChild(arr, i)].compareTo(arr[i]) > 0) {
            swap(arr, i, getMaxChild(arr, i));
            i = getMaxChild(arr, i);
        }
    }

    private static int getMaxChild(Comparable[] arr, int i) {
        if (i * 2 + 2 >= arr.length)
            return i * 2 + 1;
        return arr[i * 2 + 1].compareTo(arr[i * 2 + 2]) > 0 ? i * 2 + 1 : i * 2 + 2;
    }

    private static boolean hasChild(Comparable[] arr, int i) {
        return (i * 2 + 1) < arr.length;
    }

    private static int getParent(int i) {
        return (i - 1) / 2;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}