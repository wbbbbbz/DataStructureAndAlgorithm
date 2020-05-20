package algorithm.graph;

import java.util.ArrayList;

import datastructure.AdjSet;
import datastructure.Edge;
import datastructure.Graph;

public class FindBridges {

    private Graph G;
    private boolean[] visited;

    // 深度优先遍历的顺序
    private int[] ord;
    // 最小能达到的节点ord
    private int[] low;
    // 记录桥的结果
    private ArrayList<Edge<Integer>> bridges;
    private final int WEIGHT = 1;

    public FindBridges(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.bridges = new ArrayList<>();
        ord = new int[G.V()];
        low = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v, v);
        }
    }

    // 深度优先递归
    private void dfs(int v, int s) {
        visited[v] = true;
        ord[v] = ord[s] + 1;
        low[v] = ord[v];

        for (Integer w : G.adj(v)) {
            if (!visited[w])
                dfs(w, v);
            if (w != s && w != v) {
                low[v] = Math.min(low[w], low[v]);
                if (low[w] > ord[v])
                    bridges.add(new Edge<Integer>(w, v, WEIGHT));
            }
        }
    }

    public ArrayList<Edge<Integer>> bridges() {
        return bridges;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testBridges1.txt");
        FindBridges findBridges = new FindBridges(g);
        System.out.println(findBridges.bridges());
    }
}