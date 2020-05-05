package algorithm.sorting;

import datastructure.Array;

public class ShellSort {

    private ShellSort() {
    }

    // 希尔排序ShellSort
    // 直接对arr进行in-place sort
    public static void sort(Comparable[] arr) {

        int interval = arr.length / 3;
        for (int i = interval; i > 0; i /= 3) {
            InsertionSort.sort(arr, interval);
        }

    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}