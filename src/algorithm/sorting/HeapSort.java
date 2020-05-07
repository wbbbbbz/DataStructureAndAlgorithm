package algorithm.sorting;

import datastructure.MaxHeap;

public class HeapSort {

    // 堆排序HeapSort
    public static void sort(Comparable[] arr) {
        Integer[] temparr = new Integer[arr.length];
        for (int i = 0; i < temparr.length; i++) {
            temparr[i] = (Integer) arr[i];
        }
        MaxHeap<Integer> maxHeap = new MaxHeap<>(temparr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }
}