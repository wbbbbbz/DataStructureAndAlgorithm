package algorithm.sorting;

public class MergeSort {

    // 归并排序mergeSort
    // 需要进行递归，并且开辟新的空间
    public static void sort(Comparable[] arr) {
        // sort(arr, 0, arr.length - 1);
        sortBottomUp(arr);
    }

    // 递归的归并排序
    // 对arr数组的[left,right]部分进行排序
    private static void sort(Comparable[] arr, int left, int right) {
        // 1. 递归的基本条件
        if (right - left <= 15) {
            InsertionSort.sort(arr, left, right);
            return;
        }

        // 2. 递归逻辑
        // 这个mid偏左, [0,1]时mid=0
        // 所以分成[left,mid],[mid+1,right]
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, left, mid, right);

    }

    // 递归的归并排序-自底向上的迭代版
    // 对arr数组的[left,right]部分进行排序
    private static void sortBottomUp(Comparable[] arr) {
        // 不断进行迭代来排序
        // 也就是不断改变size，对size大小的元素进行merge
        // 最后一个元素通过min来判断越界！

        for (int size = 1; size < arr.length; size += size) {
            for (int j = 0; j + size < arr.length; j += 2 * size) {
                // 分成[i, j+size-1]和[j+size, j+2size-1]两部分
                int mid = j + size - 1;
                if (arr[mid].compareTo(arr[mid + 1]) > 0)
                    merge(arr, j, mid, Math.min(j + 2 * size - 1, arr.length - 1));
            }
        }

    }

    // 归并的实现
    private static void merge(Comparable[] arr, int left, int mid, int right) {
        // 开辟新的O(n)空间存放merge后的结果
        Comparable[] temparr = new Comparable[right - left + 1];
        int leftIndex = left;
        int rightIndex = mid + 1;
        for (int i = 0; i < temparr.length; i++) {
            if (leftIndex > mid) {
                temparr[i] = arr[rightIndex];
                rightIndex++;
            } else if (rightIndex > right) {
                temparr[i] = arr[leftIndex];
                leftIndex++;
            } else if (arr[leftIndex].compareTo(arr[rightIndex]) <= 0) {
                temparr[i] = arr[leftIndex];
                leftIndex++;
            } else {
                temparr[i] = arr[rightIndex];
                rightIndex++;
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = temparr[i - left];
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int inversionCount(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        System.arraycopy(arr, 0, aux, 0, arr.length);
        return inversionCount(arr, aux, 0, arr.length - 1);
    }

    private static int inversionCount(Comparable[] arr, Comparable[] aux, int left, int right) {
        if (left >= right)
            return 0;

        // 2. 递归逻辑
        // 这个mid偏左, [0,1]时mid=0
        // 所以分成[left,mid],[mid+1,right]
        int mid = left + (right - left) / 2;
        int count = 0;
        count += inversionCount(arr, aux, left, mid);
        count += inversionCount(arr, aux, mid + 1, right);

        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            count += inversionCountHelper(arr, aux, left, mid, right);

        return count;
    }

    private static int inversionCountHelper(Comparable[] arr, Comparable[] aux, int left, int mid, int right) {
        // 开辟新的O(n)空间存放merge后的结果
        int leftIndex = left;
        int rightIndex = mid + 1;
        // 只看右边，避免重复计算！
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (leftIndex > mid) {
                aux[i] = arr[rightIndex];
                rightIndex++;
            } else if (rightIndex > right) {
                aux[i] = arr[leftIndex];
                leftIndex++;
            } else if (arr[leftIndex].compareTo(arr[rightIndex]) <= 0) {
                aux[i] = arr[leftIndex];
                leftIndex++;
            } else {
                aux[i] = arr[rightIndex];
                rightIndex++;
                count += mid - leftIndex + 1;
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = aux[i];
        }

        return count;
    }
}