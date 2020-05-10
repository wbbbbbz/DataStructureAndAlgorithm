package datastructure.test;

import datastructure.DenseWeightedGraph;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;

public class WeightedGraphTest {

    private static final String LINE_SEPERATE = "--------------------------------------------------------------------------------------";

    public static void main(String[] args) {
        testWeightedGraph();
    }

    // 测试通过文件读取图的信息
    private static void testWeightedGraph() {

        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "testfiles\\testWG1.txt";
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(8, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println(LINE_SEPERATE);
        System.out.println("test WG1 in Sparse Weighted Graph:");
        g1.show();
        System.out.println(LINE_SEPERATE);

        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<Double>(8, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2, filename);
        System.out.println(LINE_SEPERATE);
        System.out.println("test WG1 in Dense Graph:");
        g2.show();
        System.out.println(LINE_SEPERATE);
    }
}