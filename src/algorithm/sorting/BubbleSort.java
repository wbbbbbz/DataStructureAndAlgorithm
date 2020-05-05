package algorithm.sorting;

public class BubbleSort {

    private BubbleSort() {
    }

    // 冒泡排序BubbleSort
    // 直接对arr进行in-place sort
    public static void sort(Comparable[] arr) {
        // 遍历数组，只要现在的元素比下一个元素大就交换

        // for (int i = arr.length - 1; i >= 1; i--) {
        // for (int j = 0; j < i; j++) {
        // if (arr[j].compareTo(arr[j + 1]) > 0)
        // swap(arr, j, j + 1);
        // }
        // }

        // 优化版，因为最后一次交换之后所有的元素都不需要再进行交换了，所以以最后一次交换的index作为上界
        int orderedIndex = arr.length - 1;
        for (int i = orderedIndex; i >= 1; i = orderedIndex) {
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    orderedIndex = j + 1;
                }
            }
            orderedIndex--;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}