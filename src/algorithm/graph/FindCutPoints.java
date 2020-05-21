package algorithm.graph;

import java.util.HashSet;

import datastructure.AdjSet;
import datastructure.Graph;

public class FindCutPoints {

    private Graph G;
    private boolean[] visited;

    // 深度优先遍历的顺序
    private int[] ord;
    // 最小能达到的节点ord
    private int[] low;
    // 记录桥的结果
    private HashSet<Integer> cutPoints;

    public FindCutPoints(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.cutPoints = new HashSet<>();
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

        // 孩子节点
        // w没有被访问过的话那就是一个孩子顶点
        int child = 0;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[w], low[v]);
                if (v != s && low[w] >= ord[v])
                    cutPoints.add(v);
                child++;
                if (v == s && child > 1) {
                    cutPoints.add(v);
                }
            } else if (w != s) {
                low[v] = Math.min(low[w], low[v]);
            }
        }
    }

    public HashSet<Integer> cutPoints() {
        return cutPoints;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testBridges1.txt");
        FindCutPoints findCutPoints = new FindCutPoints(g);
        System.out.println(findCutPoints.cutPoints());
    }
}