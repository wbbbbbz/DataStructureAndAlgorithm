package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import datastructure.Graph;
import datastructure.SparseGraph;

public class TopologicalSort {

    // 拓扑排序结果存储数组
    private ArrayList<Integer> topologicalSort = new ArrayList<>();

    private Graph G;
    private int[] indegrees; // 用于保存图中所有顶点的入度值
    private boolean hasCycle = true; // 检测该图是否有环
    private boolean isDAG; // 用于检测改图是否DAG图
    private boolean[] visited; // 用于深度优先遍历
    private boolean[] onPath; // 用于深度优先遍历检测环

    public TopologicalSort(Graph G) {
        this.G = G;
        this.indegrees = new int[G.V()];
        this.visited = new boolean[G.V()];
        this.onPath = new boolean[G.V()];

        if (G.isDirected()) {
            // bfs();
            for (int v = 0; v < G.V(); v++) {
                indegrees[v] = G.indegree(v);
                if (indegrees[v] == 0) {
                    hasCycle = dfs(v);
                    Collections.reverse(topologicalSort);
                    break;
                }
            }

            isDAG = !hasCycle;
            if (!isDAG) {
                topologicalSort.clear();
            }
        }
    }

    // 广度优先遍历
    private void bfs() {

        LinkedList<Integer> queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            indegrees[v] = G.indegree(v);
            if (indegrees[v] == 0) {
                queue.add(v);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            topologicalSort.add(v);
            for (Integer w : G.adj(v)) {
                if (indegrees[w] != 0) {
                    indegrees[w]--;
                    if (indegrees[w] == 0) {
                        queue.add(w);
                    }
                }
            }
        }

        // for (int v = 0; v < G.V(); v++) {
        // if (indegrees[v] != 0) {
        // hasCycle = true;
        // }
        // }

        hasCycle = topologicalSort.size() != G.V();
    }

    // 图的深度优先遍历
    // 返回的是从v开始的节点是否存在环
    private boolean dfs(int v) {
        visited[v] = true;
        onPath[v] = true;
        boolean res = false;
        // 递归
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w))
                    res = true;
            } else if (onPath[w]) {
                res = true;
            }
        }
        onPath[v] = false;
        topologicalSort.add(v);
        return res;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public boolean isDAG() {
        return isDAG;
    }

    public Iterable<Integer> topologicalSort() {
        return topologicalSort;
    }

    public static void main(String[] args) {

        Graph g2 = new SparseGraph("testfiles\\testDirectedGraph2.txt", true);
        TopologicalSort topologicalSort = new TopologicalSort(g2);
        System.out.println(topologicalSort.hasCycle());
        System.out.println(topologicalSort.isDAG());
        System.out.print("Topology Order: ");
        System.out.println(topologicalSort.topologicalSort());
    }
}