package algorithm.sorting;

public class InsertionSort {

    private InsertionSort() {
    }

    // 插入排序
    // 直接对arr进行in-place sort
    public static void sort(Comparable[] arr) {
        // 1. 已排好序的数组末尾index+1设为i
        // 2. 每一次需要交换的数组元素index设为j
        // 3. 在j-1>j的情况下交换，在j-1<=j，或者j-1=-1的情况停止交换
        // 4. 停止交换后，i++;

        // 未优化版本
        // for (int i = 1; i < arr.length; i++) {
        // for (int j = i; j > 0 && arr[j - 1].compareTo(arr[j]) > 0; j--) {
        // swap(arr, j - 1, j);
        // }
        // }

        // 优化版本1，尽可能减少交换
        for (int i = 1; i < arr.length; i++) {
            // 复制一份i的元素
            Comparable temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1].compareTo(arr[j]) > 0; j--)
                // j与j-1元素进行比较，如果arr[j-1]>arr[j]则令arr[j]=arr[j-1]
                arr[j] = arr[j - 1];
            // 此时j要么为0，要么为第一个j-1<=j的位置，这个时候赋值即可
            arr[j] = temp;
        }
    }

    // 插入排序间隔版
    // 直接对arr进行in-place sort
    public static void sort(Comparable[] arr, int interval) {

        assert interval >= 1;
        // 为了希尔排序的方法，加入interval以表示间隔
        // 优化版本1，尽可能减少交换
        for (int i = interval; i < arr.length; i++) {
            // 复制一份i的元素
            Comparable temp = arr[i];
            int j;
            for (j = i; j >= interval && arr[j - interval].compareTo(arr[j]) > 0; j -= interval)
                // j与j-interval元素进行比较，如果arr[j-interval]>arr[j]则令arr[j]=arr[j-interval]
                arr[j] = arr[j - interval];
            // 此时j要么为0，要么为第一个j-interval<=j的位置，这个时候赋值即可
            arr[j] = temp;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}