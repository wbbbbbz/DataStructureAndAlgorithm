package algorithm.graph;

import java.util.ArrayList;
import java.util.Stack;

import datastructure.AdjSet;
import datastructure.Graph;

public class GraphDFSnr {

    // 前序遍历结果存储数组
    private ArrayList<Integer> pre = new ArrayList<>();
    private Graph G;
    private boolean[] visited;

    public GraphDFSnr(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    // 深度优先遍历非递归
    private void dfs(int v) {

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;

        while (!stack.empty()) {

            int curr = stack.pop();
            // 前序遍历数组更新
            pre.add(curr);

            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testG.txt");
        GraphDFSnr graphDFS = new GraphDFSnr(g);
        System.out.println(graphDFS.pre());
    }

}