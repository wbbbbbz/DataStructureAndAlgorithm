package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;

import datastructure.AdjSet;
import datastructure.Graph;

public class HamiltonLoop {

    private Graph G;
    private int[] from;
    private int end;

    public HamiltonLoop(Graph G) {
        this.G = G;
        this.from = new int[G.V()];
        this.end = -1;
        int visited = 0;
        dfs(visited, 0, 0, G.V());
    }

    // 深度优先递归
    private boolean dfs(int visited, int v, int s, int left) {
        visited += (1 << v);
        // visited[v] = true;
        from[v] = s;
        left--;
        // // 这个时候汉密尔顿回路寻找到了
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v;
            return true;
        }
        for (Integer w : G.adj(v)) {
            if ((visited & (1 << w)) == 0) {
                if (dfs(visited, w, v, left)) {
                    return true;
                }
            }
            // else if (w == 0 && left == 0) {
            // end = v;
            // return true;
            // }
        }
        visited -= (1 << v);
        // visited[v] = false;
        return false;
    }

    public ArrayList<Integer> hamiltonLoop() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end != -1) {
            res.add(end);
            while (end != 0) {
                end = from[end];
                res.add(end);
            }
            Collections.reverse(res);
        }
        return res;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testHamiltonLoop2.txt");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(g);
        System.out.println(hamiltonLoop.hamiltonLoop());
    }
}