package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;

import datastructure.AdjSet;
import datastructure.Graph;

// 深度遍历方式求两点间的路径
public class SearchSingelPath {

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int[] from; // 保存了所有边之前的顶点的信息
    private int s; // 保存图的原点信息
    private int t; // 保存搜索路径的目标顶点信息

    // 图的深度优先遍历
    // 返回的是从v是否能达到t，v的前一个顶点是parent
    private boolean dfs(int v, int parent) {

        visited[v] = true;
        from[v] = parent;
        if (v == t)
            return true;
        // 递归
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                from[w] = v;
                if (dfs(w, v))
                    return true;
            }
        }
        return false;
    }

    // 构造函数，求无权图的路径
    public SearchSingelPath(Graph graph, int s, int t) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        G.validateVertex(s);
        G.validateVertex(t);
        this.s = s;
        this.t = t;

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        // 求图的路径
        // 原点的前一个顶点就是自己
        dfs(s, s);

    }

    // 返回有没有路径能到达t
    public boolean hasPath() {
        return visited[t];
    }

    // 输出到达节点t的路径
    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasPath())
            return res;
        while (t != s) {
            res.add(t);
            t = from[t];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    // 打印到达节点t的路径
    public void showPath() {
        System.out.print(s + " -> " + t + " : ");
        System.out.println(path());
    }

    public static void main(String[] args) {
        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testG.txt";
        Graph g = new AdjSet(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        SearchSingelPath path = new SearchSingelPath(g, 0, 6);
        System.out.println("Path from 0 to 6 : ");
        path.showPath();
        System.out.println(lineSeperator);
        SearchSingelPath path2 = new SearchSingelPath(g, 0, 1);
        System.out.println("Path from 0 to 1 : ");
        path2.showPath();
        System.out.println(lineSeperator);
        SearchSingelPath path3 = new SearchSingelPath(g, 0, 5);
        System.out.println("Path from 0 to 5 : ");
        path3.showPath();
        System.out.println(lineSeperator);
    }

}