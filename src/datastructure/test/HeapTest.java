package datastructure.test;

import java.util.Random;

import datastructure.MaxHeap;

public class HeapTest {

    public static void main(String[] args) {
        // testMaxHeap();
        int n = 100000000;
        Integer[] testData = new Integer[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        System.out.println("No Heapify " + testMaxHeap(testData, false) + " s");
        System.out.println("Heapify " + testMaxHeap(testData, true) + " s");

    }

    public static void testMaxHeap() {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();

        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException();
        }

        System.out.println("Test MaxHeap completed.");

    }

    public static double testMaxHeap(Integer[] testData, boolean isHeapify) {
        MaxHeap<Integer> maxHeap;

        long startTime = System.nanoTime();

        if (!isHeapify) {
            maxHeap = new MaxHeap<>();
            for (int i : testData)
                maxHeap.add(i);

        } else {
            maxHeap = new MaxHeap<>(testData);
        }

        long endTime = System.nanoTime();

        // int[] arr = new int[testData.length];
        // for (int i = 0; i < arr.length; i++) {
        // arr[i] = maxHeap.extractMax();
        // }

        // for (int i = 1; i < arr.length; i++) {
        // if (arr[i - 1] < arr[i])
        // throw new IllegalArgumentException();
        // }

        return (endTime - startTime) / 1000000000.0;

    }

}