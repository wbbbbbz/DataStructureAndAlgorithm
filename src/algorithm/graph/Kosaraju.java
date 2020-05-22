package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;

import datastructure.Graph;
import datastructure.SparseGraph;

public class Kosaraju {

    // 拓扑排序结果存储数组
    private ArrayList<Integer> topologicalSort = new ArrayList<>();

    private Graph G;
    private Graph reverseG;
    private boolean[] visited; // 用于深度优先遍历
    private int[] scccounts; // 用于记录每个顶点的强连通分量值
    private int scccount = 0;

    public Kosaraju(Graph G) {
        this.G = G;
        this.reverseG = G.reverse();
        this.visited = new boolean[G.V()];
        this.scccounts = new int[G.V()];

        if (G.isDirected()) {
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v])
                    reverseGDFS(v);
            }
            Collections.reverse(topologicalSort);
            visited = new boolean[G.V()];
            for (int v : topologicalSort) {
                if (!visited[v]) {
                    dfs(v, scccount);
                    scccount++;
                }
            }
        }

    }

    private void dfs(int v, int ssccount) {
        visited[v] = true;
        scccounts[v] = ssccount;
        // 递归
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, ssccount);
            }
        }
        // System.out.println(ssccount);
        // for (int i : ssccounts) {
        // System.out.print(i + " ");
        // }
        // System.out.println();
    }

    // 图的深度优先遍历
    private void reverseGDFS(int v) {
        visited[v] = true;
        // 递归
        for (Integer w : reverseG.adj(v)) {
            if (!visited[w]) {
                reverseGDFS(w);
            }
        }
        // 后序遍历结果记录
        topologicalSort.add(v);
    }

    public int scccount() {
        return scccount;
    }

    public int[] scccounts() {
        return scccounts;
    }

    public boolean isStronglyConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return scccounts[v] == scccounts[w];
    }

    public ArrayList<ArrayList<Integer>> components() {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < scccount; i++) {
            res.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < G.V(); i++) {
            res.get(scccounts[i]).add(i);
        }
        return res;
    }

    public static void main(String[] args) {

        Graph g2 = new SparseGraph("testfiles\\testDirectedGraph3.txt", true);
        Kosaraju kosaraju = new Kosaraju(g2);
        System.out.print("SSC: ");
        System.out.println(kosaraju.scccount());
        System.out.println(kosaraju.components());

    }
}