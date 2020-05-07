package algorithm.sorting.test;

import algorithm.sorting.MergeSort;

public class InversionCountTest {

    public static void main(String[] args) {
        inversionCountTest();
    }

    public static void inversionCountTest() {
        int N = 10;

        // 测试1: 测试随机数组
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        System.out.println(
                "Test Inversion Count for Random Array, n = " + N + ", result = " + MergeSort.inversionCount(arr));

        // 测试2: 测试完全有序的数组
        // 结果应该为0
        arr = SortTestHelper.generateOrderedArray(N);
        System.out.println(
                "Test Inversion Count for Ordered Array, n = " + N + ", result = " + MergeSort.inversionCount(arr));

        // 测试3: 测试完全逆序的数组
        // 结果应改为 N*(N-1)/2
        arr = SortTestHelper.generateInversedArray(N);
        System.out.println(
                "Test Inversion Count for Inversed Array, n = " + N + ", result = " + MergeSort.inversionCount(arr));

        return;
    }

}