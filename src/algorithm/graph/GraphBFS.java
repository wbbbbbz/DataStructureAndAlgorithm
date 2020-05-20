package algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;

import datastructure.AdjSet;
import datastructure.Graph;

public class GraphBFS {

    // 广度优先遍历结果存储数组
    private ArrayList<Integer> order = new ArrayList<>();

    private Graph G;
    private boolean[] visited;

    public GraphBFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!visited[s])
                bfs(s);
        }
    }

    // 广度优先遍历
    private void bfs(int s) {
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.add(v);
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testG1.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.print("BFS Order: ");
        System.out.println(graphBFS.order());
    }

}