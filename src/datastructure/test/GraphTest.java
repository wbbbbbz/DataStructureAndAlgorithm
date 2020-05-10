package datastructure.test;

import java.util.Random;

import datastructure.DenseGraph;
import datastructure.SparseGraph;

public class GraphTest {

    public static void main(String[] args) {
        testGraphIterator();
    }

    private static void testGraphIterator() {

        int N = 20;
        int M = 100;
        Random random = new Random();

        SparseGraph g1 = new SparseGraph(N, false);
        DenseGraph g2 = new DenseGraph(N, false);
        for (int i = 0; i < M; i++)
            g1.addEdge(random.nextInt(N), random.nextInt(N));

        for (int i = 0; i < M; i++)
            g2.addEdge(random.nextInt(N), random.nextInt(N));

        System.out.println("-------------------------------------------");

        System.out.println("Edges of SparseGraph.");
        for (int v = 0; v < N; v++) {
            System.out.print(v + ": ");
            for (Integer i : g1.adj(v))
                System.out.print(i + ", ");
            System.out.println();
        }
        System.out.println("-------------------------------------------");

        System.out.println("Edges of DenseGraph.");
        for (int v = 0; v < N; v++) {
            System.out.print(v + ": ");
            for (Integer i : g2.adj(v))
                System.out.print(i + ", ");
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }

}