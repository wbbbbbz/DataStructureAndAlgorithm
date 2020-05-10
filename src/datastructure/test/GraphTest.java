package datastructure.test;

import java.util.Random;

import datastructure.DenseGraph;
import datastructure.ReadGraph;
import datastructure.SparseGraph;

public class GraphTest {

    public static void main(String[] args) {
        // testGraphIterator();
        showGraph();
    }

    private static void testGraphIterator() {

        String lineSeperator = "--------------------------------------------------------------------------------------";

        int N = 20;
        int M = 100;
        Random random = new Random();

        SparseGraph g1 = new SparseGraph(N, false);
        DenseGraph g2 = new DenseGraph(N, false);
        for (int i = 0; i < M; i++)
            g1.addEdge(random.nextInt(N), random.nextInt(N));

        for (int i = 0; i < M; i++)
            g2.addEdge(random.nextInt(N), random.nextInt(N));

        System.out.println(lineSeperator);

        System.out.println("Edges of SparseGraph.");
        for (int v = 0; v < N; v++) {
            System.out.print(v + ": ");
            for (Integer i : g1.adj(v))
                System.out.print(i + ", ");
            System.out.println();
        }
        System.out.println(lineSeperator);

        System.out.println("Edges of DenseGraph.");
        for (int v = 0; v < N; v++) {
            System.out.print(v + ": ");
            for (Integer i : g2.adj(v))
                System.out.print(i + ", ");
            System.out.println();
        }
        System.out.println(lineSeperator);
    }

    // 测试通过文件读取图的信息
    public static void showGraph() {

        String lineSeperator = "--------------------------------------------------------------------------------------";
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "testfiles\\testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename);
        System.out.println(lineSeperator);
        System.out.println("test G1 in Sparse Graph:");
        g1.show();

        System.out.println(lineSeperator);

        DenseGraph g2 = new DenseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println(lineSeperator);

        // 使用两种图的存储方式读取testG2.txt文件
        filename = "testfiles\\testG2.txt";
        SparseGraph g3 = new SparseGraph(6, false);
        ReadGraph readGraph3 = new ReadGraph(g3, filename);
        System.out.println("test G2 in Sparse Graph:");
        g3.show();

        System.out.println(lineSeperator);

        DenseGraph g4 = new DenseGraph(6, false);
        ReadGraph readGraph4 = new ReadGraph(g4, filename);
        System.out.println("test G2 in Dense Graph:");
        g4.show();
        System.out.println(lineSeperator);
    }

}