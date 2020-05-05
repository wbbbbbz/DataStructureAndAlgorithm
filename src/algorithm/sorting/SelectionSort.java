package algorithm.sorting;

public class SelectionSort {

    private SelectionSort() {
    }

    // 选择排序selectionSort
    // 直接对arr进行in-place sort
    public static void sort(Comparable[] arr) {
        // 1. 遍历数组，每一次确定第一个未排序元素
        // 2. nested遍历，每一次找到第一个未排序元素，并且交换
        // for (int i = 0; i < arr.length - 1; i++) {
        // int indexSelected = i;
        // for (int j = i + 1; j < arr.length; j++) {
        // if (arr[j].compareTo(arr[indexSelected]) < 0) {
        // indexSelected = j;
        // }
        // }
        // swap(arr, indexSelected, i);
        // }

        // 优化版本：同时对最小值和最大值进行排序
        for (int head = 0, tail = arr.length - 1; head < tail; head++, tail--) {
            int minIndex = head;
            int maxIndex = tail;
            for (int i = head; i <= tail; i++) {
                minIndex = arr[i].compareTo(arr[minIndex]) < 0 ? i : minIndex;
                maxIndex = arr[i].compareTo(arr[maxIndex]) > 0 ? i : maxIndex;
            }
            swap(arr, head, minIndex);
            swap(arr, tail, maxIndex);
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}