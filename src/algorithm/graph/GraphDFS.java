package algorithm.graph;

import java.util.ArrayList;

import datastructure.AdjSet;
import datastructure.Graph;
import datastructure.SparseGraph;

public class GraphDFS {

    // 前序遍历结果存储数组
    private ArrayList<Integer> pre = new ArrayList<>();
    // 后序遍历结果存储数组
    private ArrayList<Integer> post = new ArrayList<>();

    private Graph G;
    private boolean[] visited;

    public GraphDFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    // 深度优先递归
    private void dfs(int v) {
        visited[v] = true;
        // 前序遍历数组更新
        pre.add(v);

        for (Integer w : G.adj(v)) {
            if (!visited[w])
                dfs(w);
        }
        // 后序遍历数组更新
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {

        Graph g1 = new AdjSet("testfiles\\testDirectedGraph1.txt");
        GraphDFS graphDFS = new GraphDFS(g1);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());

        Graph g2 = new SparseGraph("testfiles\\testDirectedGraph1.txt", true);
        graphDFS = new GraphDFS(g2);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }

}