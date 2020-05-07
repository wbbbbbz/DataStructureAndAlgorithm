package datastructure.test;

import datastructure.IndexMaxHeap;

public class IndexMaxHeapTest {

    public static void main(String[] args) {
        indexMaxHeapTest();
    }

    private static void indexMaxHeapTest() {
        int N = 1000000;
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<Integer>(N);
        for (int i = 0; i < N; i++)
            indexMaxHeap.add((int) (Math.random() * N));
        assert indexMaxHeap.testIndexes();
        System.out.println("\nIndexMaxHeap test is finished!");
    }

}