package algorithm.graph;

import java.util.LinkedList;

import datastructure.AdjSet;
import datastructure.Graph;

// 深度遍历方式求是否存在环
public class CycleDetection {

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int[] from; // 保存了所有边之前的顶点的信息
    private boolean hasCycled; // 记录图中是否有环

    // 图的深度优先遍历
    // 返回的是从v开始的节点是否存在环
    private boolean dfs(int v, int parent) {

        visited[v] = true;
        from[v] = parent;
        // 递归
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                from[w] = v;
                if (dfs(w, v))
                    return true;
            } else if (parent != w) {
                return true;
            }
        }
        return false;
    }

    // 图的广度优先遍历
    // 返回的是从s开始的图是否有环
    private boolean bfs(int s, int parent) {

        visited[s] = true;
        from[s] = parent;
        // 广度优先遍历
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    from[w] = v;
                    visited[w] = true;
                    queue.push(w);
                } else if (w != from[v]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 构造函数，求无权图的路径
    public CycleDetection(Graph graph) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        from = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        // 遍历图
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                if (bfs(v, v)) {
                    hasCycled = true;
                    break;
                }
            ;
        }

    }

    // 返回图中是否包括环
    public boolean hasCycled() {
        return hasCycled;
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG.txt";
        Graph g = new AdjSet(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        CycleDetection cycleDection = new CycleDetection(g);
        System.out.println(cycleDection.hasCycled());
    }

}