package algorithm.sorting.test;

import java.lang.reflect.Method;

public class SortTestHelper {

    // 不生成实例
    private SortTestHelper() {
    }

    // 生成n个范围是[rangeL, rangeR]的随机数组
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);

        return arr;
    }

    // 打印arr数组的所有内容
    public static void printArray(Object arr[]) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();

        return;
    }

    // 判断arr数组是否有序
    public static boolean isSorted(Comparable[] arr) {

        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i].compareTo(arr[i + 1]) > 0)
                return false;
        return true;
    }

    // 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.valueOf(i);

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, Comparable[] arr) {

        // 通过Java的反射机制和排序的类名，运行排序函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort", new Class[] { Comparable[].class });
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[] { arr };

            long startTime = System.nanoTime();
            // 调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.nanoTime();

            assert isSorted(arr);

            System.out.println(sortClass.getSimpleName() + " : " + (endTime - startTime) / 1000000.0 + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    // 将算法的运行时间以long类型返回, 单位为毫秒(ms)
    public static long testSort2(String sortClassName, Comparable[] arr) {

        // 通过Java的反射机制，通过排序的类名，运行排序函数
        try {
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort", new Class[] { Comparable[].class });
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[] { arr };

            long startTime = System.nanoTime();
            // 调用排序函数
            sortMethod.invoke(null, params);
            long endTime = System.nanoTime();

            assert isSorted(arr);

            return (long) ((endTime - startTime) / 1000000.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 生成一个完全有序的数组
    public static Integer[] generateOrderedArray(int n) {

        return generateNearlyOrderedArray(n, 0);
    }

    // 生成一个完全逆序的数组
    public static Integer[] generateInversedArray(int n) {

        Integer[] arr = generateOrderedArray(n);
        for (int i = n / 2 - 1; i >= 0; i--) {
            Integer t = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = t;
        }
        return arr;
    }

    // 将数组arr随机化
    public static void shuffleArray(Object[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int j = (int) (Math.random() * (n - i)) + i;

            Object t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}
