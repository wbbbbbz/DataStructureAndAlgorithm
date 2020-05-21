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
    private int end;

    public HamiltonPath(Graph G, int start) {
        this.G = G;
        G.validateVertex(start);
        this.start = start;
        this.visited = new boolean[G.V()];
        this.from = new int[G.V()];
        this.end = -1;
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
            end = v;
            return true;
        }
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) {
                    return true;
                }
            }
        }
        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> hamiltorPath() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end != -1) {
            res.add(end);
            while (end != start) {
                end = from[end];
                res.add(end);
            }
            Collections.reverse(res);
        }
        return res;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testHamiltonPath1.txt");
        HamiltonPath hamiltonPath = new HamiltonPath(g, 2);
        System.out.println(hamiltonPath.hamiltorPath());
    }
}