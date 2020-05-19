package algorithm.graph.test;

import algorithm.graph.SingleSourcePath;
import datastructure.ReadGraph;
import datastructure.SparseGraph;

public class PathTest {

    public static void main(String[] args) {
        testPath();
    }

    // 测试寻路算法
    private static void testPath() {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        SingleSourcePath path = new SingleSourcePath(g, 0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
        System.out.println(lineSeperator);

    }

}