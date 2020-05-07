package algorithm.sorting;

import datastructure.IndexMaxHeap;

public class IndexHeapSort {

    // 算法类不允许产生任何实例
    private IndexHeapSort() {
    }

    public static void sort(Comparable[] arr) {

        int n = arr.length;

        Integer[] temparr = new Integer[n];
        for (int i = 0; i < n; i++) {
            temparr[i] = (Integer) arr[i];
        }

        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(temparr);

        for (int i = n - 1; i >= 0; i--)
            arr[i] = indexMaxHeap.extractMax();
    }

}