package algorithm.graph.test;

import algorithm.graph.Dijkstra;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;

public class DijkstraTest {

    public static void main(String[] args) {
        testDijkstra();
    }

    // 测试我们的Dijkstra最短路径算法
    private static void testDijkstra() {

        String filename = "testfiles\\testWG5.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
        // SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:\n");
        Dijkstra<Integer> dij = new Dijkstra<Integer>(g, 0);
        for (int i = 1; i < V; i++) {
            if (dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            } else
                System.out.println("No Path to " + i);

            System.out.println("----------");
        }

    }

}