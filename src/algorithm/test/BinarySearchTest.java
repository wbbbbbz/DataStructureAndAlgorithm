package algorithm.test;

import algorithm.BinarySearch;

public class BinarySearchTest {

    public static void main(String[] args) {
        binarySearchTest();
        floorAndCeilTest();
    }

    private static void binarySearchTest() {
        int N = 1000000;
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.valueOf(i);

        // 测试非递归二分查找法
        long startTime = System.nanoTime();

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for (int i = 0; i < 2 * N; i++) {
            int v = BinarySearch.binarySearchNR(arr, Integer.valueOf(i));
            if (i < N)
                assert v == i;
            else
                assert v == -1;
        }
        long endTime = System.nanoTime();

        System.out.println("\nBinary Search (Without Recursion): " + (endTime - startTime) / 1000000.0 + "ms");

        // 测试递归的二分查找法
        startTime = System.nanoTime();

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for (int i = 0; i < 2 * N; i++) {
            int v = BinarySearch.binarySearch(arr, Integer.valueOf(i));
            if (i < N)
                assert v == i;
            else
                assert v == -1;
        }
        endTime = System.nanoTime();
        System.out.println("\nBinary Search (With Recursion): " + (endTime - startTime) / 1000000.0 + "ms");
    }

    private static void floorAndCeilTest() {
        Integer arr[] = new Integer[] { 1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 5, 5, 5, 6, 6, 6 };
        for (int i = 0; i <= 8; i++) {

            int floorIndex = BinarySearch.floor(arr, i);
            System.out.println("the floor index of " + i + " is " + floorIndex + ".");
            if (floorIndex >= 0 && floorIndex < arr.length)
                System.out.println("The value is " + arr[floorIndex] + ".");
            System.out.println();

            int ceilIndex = BinarySearch.ceil(arr, i);
            System.out.println("the ceil index of " + i + " is " + ceilIndex + ".");
            if (ceilIndex >= 0 && ceilIndex < arr.length)
                System.out.println("The value is " + arr[ceilIndex] + ".");
            System.out.println();

            System.out.println();
        }
    }
}