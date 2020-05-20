package algorithm.graph;

import java.util.LinkedList;

import datastructure.AdjSet;
import datastructure.Graph;

// 深度遍历方式求是否存在环
public class BipartitionDetection {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private boolean isBipartite; // 记录图是否二分图
    private boolean[] colors; // 记录图中每一个顶点的染色情况

    // 图的深度优先遍历
    // 返回以v为顶点的图是否二分图
    private boolean dfs(int v, boolean color) {

        visited[v] = true;
        colors[v] = color;
        // 递归
        for (Integer w : G.adj(v)) {
            if (!visited[w] && !dfs(w, !color))
                return false;
            if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    // 图的深度优先遍历
    // 返回以s为顶点的图是否二分图
    private boolean bfs(int s, boolean color) {

        visited[s] = true;
        colors[s] = color;
        // 广度优先遍历
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    colors[w] = !colors[v];
                    queue.push(w);
                } else if (colors[w] == colors[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 构造函数，给无权图染色
    public BipartitionDetection(Graph graph) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        colors = new boolean[G.V()];
        isBipartite = true;

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            colors[i] = false;
        }

        // 遍历图
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!bfs(v, RED)) {
                    isBipartite = false;
                    break;
                }
            }
        }

    }

    // 返回图中是否包括环
    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG2.txt";
        Graph g = new AdjSet(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite());
    }

}