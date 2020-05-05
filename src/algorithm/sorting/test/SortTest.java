package algorithm.sorting.test;

import algorithm.sorting.SelectionSort;

public class SortTest {
    public static void main(String[] args) {
        // sortTest();
        int n = 10000;
        System.out.println("\nTime of sorting " + n + " size completely random array");
        System.out.println("----------------------------------------------------------------------------------");
        SortTestHelper.testSort("algorithm.sorting.SelectionSort", SortTestHelper.generateRandomArray(n, 0, n));
        SortTestHelper.testSort("algorithm.sorting.InsertionSort", SortTestHelper.generateRandomArray(n, 0, n));
        SortTestHelper.testSort("algorithm.sorting.BubbleSort", SortTestHelper.generateRandomArray(n, 0, n));
        SortTestHelper.testSort("algorithm.sorting.ShellSort", SortTestHelper.generateRandomArray(n, 0, n));

        System.out.println("\nTime of sorting " + n + " size nearly ordered array");
        System.out.println("----------------------------------------------------------------------------------");
        SortTestHelper.testSort("algorithm.sorting.SelectionSort", SortTestHelper.generateNearlyOrderedArray(n, 10));
        SortTestHelper.testSort("algorithm.sorting.InsertionSort", SortTestHelper.generateNearlyOrderedArray(n, 10));
        SortTestHelper.testSort("algorithm.sorting.BubbleSort", SortTestHelper.generateNearlyOrderedArray(n, 10));
        SortTestHelper.testSort("algorithm.sorting.ShellSort", SortTestHelper.generateNearlyOrderedArray(n, 10));
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
}