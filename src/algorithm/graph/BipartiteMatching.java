package algorithm.graph;

import datastructure.Graph;
import datastructure.SparseGraph;
import datastructure.SparseWeightedGraph;

public class BipartiteMatching {

    private Graph G;
    private int maxMatching;

    public BipartiteMatching(Graph G) {

        BipartitionDetection bd = new BipartitionDetection(G);
        assert (bd.isBipartite());

        this.G = G;

        boolean[] colors = bd.colors();

        // 源点为V，汇点为V+1
        SparseWeightedGraph<Integer> network = new SparseWeightedGraph<>(G.V() + 2, true);
        for (int v = 0; v < colors.length; v++) {
            if (colors[v])
                network.addEdge(G.V(), v, 1);
            else
                network.addEdge(v, G.V() + 1, 1);
            for (int w : G.adj(v)) {
                // 避免无向图的重复添加边
                if (v < w) {
                    if (colors[v])
                        network.addEdge(v, w, 1);
                    else
                        network.addEdge(w, v, 1);
                }
            }
        }

        EdmondKarp<Integer> maxFlow = new EdmondKarp<>(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();

    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testMaxMatching2.txt";
        Graph g = new SparseGraph(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        BipartiteMatching bipartiteMatching = new BipartiteMatching(g);
        System.out.println(bipartiteMatching.maxMatching());
        System.out.println(bipartiteMatching.isPerfectMatching());
    }
}