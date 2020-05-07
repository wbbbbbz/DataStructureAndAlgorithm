package algorithm.sorting;

import java.util.Random;

public class QuickSort {

    public static final boolean LARGEST = true;
    public static final boolean SMALLEST = false;

    private QuickSort() {
    };

    // 快速排序QuickSort
    // 需要进行递归
    public static void sort(Comparable[] arr) {
        // 使用左闭右开方式进行递归
        sort(arr, 0, arr.length);
    }

    // 快速排序递归思路
    // 左闭右开
    private static void sort(Comparable[] arr, int left, int right) {
        // 递归基本条件
        if (right - left <= 16) {
            InsertionSort.sort(arr, left, right - 1);
            return;
        }

        // // 递归
        // // 先定义pivot，并且进行partition
        // // 然后左右各自sort
        // int pivot = partition2(arr, left, right);
        // sort(arr, left, pivot);
        // sort(arr, pivot + 1, right);

        int[] ltRt = partition3(arr, left, right);
        sort(arr, left, ltRt[0] + 1);
        sort(arr, ltRt[1], right);
    }

    // partition，返回pivot的正确索引
    private static int partition(Comparable[] arr, int left, int right) {
        // 定义pivot为left的元素，并以此为标准开始partition
        Random random = new Random();
        swap(arr, left, random.nextInt(right - left) + left);
        int pivot = left;
        for (int i = left + 1; i < right; i++) {
            if (arr[i].compareTo(arr[pivot]) < 0) {
                swap(arr, i, pivot);
                pivot++;
            }
        }
        return pivot;
    }

    // partition，返回pivot的正确索引
    // 第2版，用来解决重复元素的问题
    // 左右同时进行扫描，直到交换
    private static int partition2(Comparable[] arr, int left, int right) {
        // 定义pivot为left的元素，并以此为标准开始partition
        Random random = new Random();
        swap(arr, left, random.nextInt(right - left) + left);
        int pivotIndex = left;
        Comparable pivot = arr[left];
        int i = left + 1;
        int j = right - 1;
        while (true) {
            while (i <= right && arr[i].compareTo(pivot) < 0)
                i++;
            while (j >= left + 1 && arr[j].compareTo(pivot) > 0)
                j--;
            if (i > j)
                break;
            swap(arr, i, j);
            i++;
            j--;

        }
        swap(arr, pivotIndex, j);
        return j;
    }

    // partition，返回pivot的正确索引
    // 第3版，quick sort 3 ways
    // < = > 同时分组
    // [left, lt-1] < V, [lt, gt-1] = V, [gt, right-1] > V
    private static int[] partition3(Comparable[] arr, int left, int right) {
        // 定义pivot为left的元素，并以此为标准开始partition
        Random random = new Random();
        swap(arr, left, random.nextInt(right - left) + left);

        int pivotIndex = left;
        Comparable pivot = arr[left];

        int lt = left;
        int gt = right - 1;
        int i = left + 1;
        while (i < gt) {
            if (arr[i].compareTo(pivot) < 0)
                swap(arr, i++, ++lt);
            else if (arr[i].compareTo(pivot) > 0)
                swap(arr, i, --gt);
            else
                i++;
        }
        swap(arr, left, lt);
        return new int[] { lt - 1, gt };
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Comparable selectNth(Comparable[] arr, int n, boolean isLargest) {

        if (isLargest == LARGEST)
            n = arr.length - n;
        else
            n--;

        int left = 0;
        int right = arr.length;
        while (true) {
            int[] ltRt = partition3(arr, left, right);
            if (n > ltRt[0] && n < ltRt[1])
                return arr[ltRt[0] + 1];
            if (n <= ltRt[0])
                right = ltRt[0] + 1;
            else
                left = ltRt[1];
        }
    }
}