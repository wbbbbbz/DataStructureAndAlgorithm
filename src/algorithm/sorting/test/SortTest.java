package algorithm.sorting.test;

import java.util.Arrays;

import algorithm.sorting.SelectionSort;

public class SortTest {
    public static void main(String[] args) {
        // sortTest();
        sortTest2();
        // sortTest3();
    }

    private static void sortTest2() {
        // int n = 10000;
        // System.out.println("\nTime of sorting " + n + " size completely random
        // array");
        // System.out.println("----------------------------------------------------------------------------------");
        // SortTestHelper.testSort("algorithm.sorting.SelectionSort",
        // SortTestHelper.generateRandomArray(n, 0, n));
        // SortTestHelper.testSort("algorithm.sorting.InsertionSort",
        // SortTestHelper.generateRandomArray(n, 0, n));
        // SortTestHelper.testSort("algorithm.sorting.BubbleSort",
        // SortTestHelper.generateRandomArray(n, 0, n));
        // SortTestHelper.testSort("algorithm.sorting.ShellSort",
        // SortTestHelper.generateRandomArray(n, 0, n));

        // System.out.println("\nTime of sorting " + n + " size nearly ordered array");
        // System.out.println("----------------------------------------------------------------------------------");
        // SortTestHelper.testSort("algorithm.sorting.SelectionSort",
        // SortTestHelper.generateNearlyOrderedArray(n, 10));
        // SortTestHelper.testSort("algorithm.sorting.InsertionSort",
        // SortTestHelper.generateNearlyOrderedArray(n, 10));
        // SortTestHelper.testSort("algorithm.sorting.BubbleSort",
        // SortTestHelper.generateNearlyOrderedArray(n, 10));
        // SortTestHelper.testSort("algorithm.sorting.ShellSort",
        // SortTestHelper.generateNearlyOrderedArray(n, 10));

        int m = 1000000;
        System.out.println("\nTime of sorting " + m + " size completely random array");
        System.out.println("----------------------------------------------------------------------------------");
        SortTestHelper.testSort("algorithm.sorting.MergeSort", SortTestHelper.generateRandomArray(m, 0, 10));
        SortTestHelper.testSort("algorithm.sorting.QuickSort", SortTestHelper.generateRandomArray(m, 0, 10));
        SortTestHelper.testSort("algorithm.sorting.HeapSort", SortTestHelper.generateRandomArray(m, 0, 10));

        int swapTimes = 10;
        System.out.println(
                "\nTime of sorting " + m + " size nearly ordered array with " + swapTimes + " elements disordered");
        System.out.println("----------------------------------------------------------------------------------");
        SortTestHelper.testSort("algorithm.sorting.MergeSort", SortTestHelper.generateNearlyOrderedArray(m, swapTimes));
        SortTestHelper.testSort("algorithm.sorting.QuickSort", SortTestHelper.generateNearlyOrderedArray(m, swapTimes));
        SortTestHelper.testSort("algorithm.sorting.HeapSort", SortTestHelper.generateNearlyOrderedArray(m, swapTimes));
        SortTestHelper.testSort("algorithm.sorting.InsertionSort",
                SortTestHelper.generateNearlyOrderedArray(m, swapTimes));
    }

    public static void sortTest() {
        // 测试Integer
        int n = 10000;
        Integer[] a = SortTestHelper.generateRandomArray(n, 0, n);
        SelectionSort.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            System.out.print(' ');
        }
        System.out.println();

        // 测试Double
        Double[] b = { 4.4, 3.3, 2.2, 1.1 };
        SelectionSort.sort(b);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
            System.out.print(' ');
        }
        System.out.println();

        // 测试String
        String[] c = { "D", "C", "B", "A" };
        SelectionSort.sort(c);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
            System.out.print(' ');
        }
        System.out.println();

        // 测试自定义的类 Student
        Student[] d = new Student[4];
        d[0] = new Student("D", 90);
        d[1] = new Student("C", 100);
        d[2] = new Student("B", 95);
        d[3] = new Student("A", 95);
        SelectionSort.sort(d);
        for (int i = 0; i < d.length; i++)
            System.out.println(d[i]);
    }

    public static void sortTest3() {
        // 测试T个测试用例, 每个测试用例的数组大小为n
        int T = 100;
        int N = 1000000;

        // 比较 Shell Sort 和 Merge Sort 和 三种 Quick Sort 的性能效率
        long time1 = 0, time2 = 0, time3 = 0;
        for (int i = 0; i < T; i++) {
            Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, N);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

            time1 += SortTestHelper.testSort2("algorithm.sorting.ShellSort", arr1);
            time2 += SortTestHelper.testSort2("algorithm.sorting.MergeSort", arr2);
            time3 += SortTestHelper.testSort2("algorithm.sorting.QuickSort", arr3);
        }
        System.out.println("Sorting " + N + " elements " + T + " times. Calculate the average run time.");
        System.out.println("Shell Sort Average Run Time: " + time1 / T + " ms");
        System.out.println("Merge Sort Average Run Time: " + time2 / T + " ms");
        System.out.println("Quick Sort Average Run Time: " + time3 / T + " ms");

        return;
    }
}