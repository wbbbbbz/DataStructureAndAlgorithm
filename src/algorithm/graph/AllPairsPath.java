package algorithm.graph;

import datastructure.AdjSet;
import datastructure.Graph;

// 深度遍历方式求两点间的路径
public class AllPairsPath {

    Graph G; // 图的引用
    private SingleSourcePath[] paths;

    // 构造函数，求无权图的路径
    public AllPairsPath(Graph graph) {

        // 初始化
        G = graph;
        paths = new SingleSourcePath[G.V()];
        for (int s = 0; s < G.V(); s++) {
            paths[s] = new SingleSourcePath(G, s);
        }

    }

    // 返回有没有路径能从s到达v
    public boolean hasPath(int s, int v) {
        G.validateVertex(s);
        return paths[s].hasPath(v);
    }

    // 输出从s到达节点v的路径
    public Iterable<Integer> path(int s, int v) {
        G.validateVertex(s);
        return paths[s].path(v);
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG.txt";
        Graph g = new AdjSet(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        AllPairsPath paths = new AllPairsPath(g);
        System.out.println("Path from 0 to 6 : ");
        System.out.println(paths.path(0, 6));
        System.out.println(lineSeperator);
        System.out.println("Path from 1 to 5 : ");
        System.out.println(paths.path(1, 5));
        System.out.println(lineSeperator);
        System.out.println("Path from 2 to 4 : ");
        System.out.println(paths.path(2, 4));
        System.out.println(lineSeperator);
        System.out.println("Path from 3 to 1 : ");
        System.out.println(paths.path(3, 1));
        System.out.println(lineSeperator);
    }

}