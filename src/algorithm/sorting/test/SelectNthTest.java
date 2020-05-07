package algorithm.sorting.test;

import algorithm.sorting.QuickSort;

public class SelectNthTest {

    public static void main(String[] args) {
        selectNthTest();
    }

    private static void selectNthTest() {
        // 生成一个大小为n, 包含0...n-1这n个元素的随机数组arr
        int N = 10000;
        Integer[] arr = SortTestHelper.generateOrderedArray(N);
        SortTestHelper.shuffleArray(arr);

        // 验证Selection.solve, 对arr数组求第i小元素, 应该为i
        for (int i = 0; i < N; i++) {
            assert (Integer) QuickSort.selectNth(arr, i, QuickSort.SMALLEST) == i;
            System.out.println("test " + i + " complete.");
        }

        for (int i = 0; i < N; i++) {
            assert (Integer) QuickSort.selectNth(arr, i, QuickSort.LARGEST) == N - i - 1;
            System.out.println("test " + (N - i - 1) + " complete.");
        }
        System.out.println("Test Selection.solve completed.");
        System.out.println();

        arr = SortTestHelper.generateOrderedArray(N);
        SortTestHelper.shuffleArray(arr);
    }

}