package algorithm.graph.test;

import algorithm.graph.SingleSourcePath;
import algorithm.graph.ShortestPath;
import datastructure.ReadGraph;
import datastructure.SparseGraph;

public class ShortestPathTest {

    public static void main(String[] args) {
        testShortestPath();
    }

    // 测试无权图最短路径算法
    public static void testShortestPath() {
        String lineSeperator = "--------------------------------------------------------------------------------------";
        String filename = "testfiles\\testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        SingleSourcePath dfs = new SingleSourcePath(g, 0);
        System.out.print("DFS : ");
        dfs.showPath(6);
        System.out.println(lineSeperator);

        ShortestPath bfs = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(6);
        System.out.println(lineSeperator);

        filename = "testfiles\\testG1.txt";
        SparseGraph g2 = new SparseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        g2.show();
        System.out.println(lineSeperator);

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        SingleSourcePath dfs2 = new SingleSourcePath(g2, 0);
        System.out.print("DFS : ");
        dfs2.showPath(3);
        System.out.println(lineSeperator);

        ShortestPath bfs2 = new ShortestPath(g, 0);
        System.out.print("BFS : ");
        bfs.showPath(3);
        System.out.println(lineSeperator);
    }

}