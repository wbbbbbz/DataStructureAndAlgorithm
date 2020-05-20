package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import datastructure.AdjSet;
import datastructure.Graph;

// Unweighted Single Source Shortest Path
public class USSSPath {

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int[] from; // 保存了所有边之前的顶点的信息
    private int[] dis; // 保存了从顶点到所有边最短距离的信息
    private int s; // 保存图的原点信息

    // 图的广度优先遍历
    void bfs(int s) {

        visited[s] = true;
        from[s] = s;
        // 广度优先遍历
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(s);
        dis[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    from[w] = v;
                    visited[w] = true;
                    dis[w] = dis[v] + 1;
                    queue.push(w);
                }
            }
        }
    }

    // 构造函数，求无权图的路径
    public USSSPath(Graph graph, int s) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        dis = new int[G.V()];
        this.s = s;

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            dis[i] = -1;
        }

        // 求图的路径
        bfs(s);

    }

    // 返回有没有路径能到达w
    public boolean hasPath(int w) {
        G.validateVertex(w);
        return visited[w];
    }

    public int dis(int t) {
        G.validateVertex(t);
        return dis[t];
    }

    // 输出到达节点w的路径
    public Iterable<Integer> path(int w) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasPath(w))
            return res;
        while (w != s) {
            res.add(w);
            w = from[w];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    // 打印到达节点w的路径
    public void showPath(int w) {
        System.out.print(s + " -> " + w + " : ");
        System.out.println(path(w));
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG.txt";
        Graph g = new AdjSet(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        USSSPath path = new USSSPath(g, 0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);
        System.out.println(path.dis(6));
        System.out.println(lineSeperator);
        System.out.println("Path from 0 to 5 : ");
        path.showPath(5);
        System.out.println(path.dis(5));
        System.out.println(lineSeperator);
        System.out.println("Path from 0 to 4 : ");
        path.showPath(4);
        System.out.println(path.dis(4));
        System.out.println(lineSeperator);
    }
}