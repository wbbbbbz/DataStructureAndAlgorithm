package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;

import datastructure.AdjSet;
import datastructure.Graph;

public class HamiltonPath {

    private Graph G;
    private boolean[] visited;
    private int[] from;
    private int start;
    private ArrayList<ArrayList<Integer>> paths;
    private int pathCount;

    public HamiltonPath(Graph G, int start) {
        this.G = G;
        G.validateVertex(start);
        this.start = start;
        this.visited = new boolean[G.V()];
        this.from = new int[G.V()];
        this.paths = new ArrayList<>();
        dfs(start, start, G.V());
    }

    // 深度优先递归
    // 返回是否有哈密尔顿路径
    private boolean dfs(int v, int s, int left) {
        visited[v] = true;
        from[v] = s;
        left--;
        // // 这个时候汉密尔顿路径寻找到了
        if (left == 0) {
            ArrayList<Integer> res = new ArrayList<>();
            int curr = v;
            if (curr != -1) {
                res.add(curr);
                while (curr != start) {
                    curr = from[curr];
                    res.add(curr);
                }
                Collections.reverse(res);
            }
            paths.add(res);
            pathCount++;
            visited[v] = false;
            return true;
        }
        boolean hasPath = false;
        for (Integer w : G.adj(v)) {
            // System.out.println(w);
            if (!visited[w]) {
                hasPath |= dfs(w, v, left);
                // System.out.println(v + " " + w + " " + hasPath + " " + left);
            }
        }
        visited[v] = false;
        return hasPath;
    }

    // public ArrayList<Integer> hamiltorPath() {
    // ArrayList<Integer> res = new ArrayList<>();
    // int curr = end;
    // if (curr != -1) {
    // res.add(curr);
    // while (curr != start) {
    // curr = from[curr];
    // res.add(curr);
    // }
    // Collections.reverse(res);
    // }
    // return res;
    // }

    public ArrayList<ArrayList<Integer>> hamiltorPaths() {
        return paths;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testHamiltonLoop2.txt");
        HamiltonPath hamiltonPath = new HamiltonPath(g, 0);
        System.out.println(hamiltonPath.hamiltorPaths());
    }
}