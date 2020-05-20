package algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;

import datastructure.AdjSet;
import datastructure.Graph;

// 求图的连通分量的算法类
public class Components {

    Graph G; // 图的引用
    private int cccount; // 记录联通分量个数
    private int[] visited; // 每个节点所对应的联通分量标记

    // 图的深度优先遍历
    void dfs(int v) {

        visited[v] = cccount;
        // 递归
        for (Integer i : G.adj(v)) {
            if (visited[i] == -1)
                dfs(i);
        }
    }

    // 图的广度优先遍历
    void bfs(int s) {

        visited[s] = cccount;
        // 广度优先遍历
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer w : G.adj(v)) {
                if (visited[w] == -1) {
                    visited[w] = cccount;
                    queue.add(w);
                }
            }
        }
    }

    // 构造函数，求无权图的联通分量
    public Components(Graph graph) {

        // 初始化
        G = graph;
        visited = new int[G.V()];
        cccount = 0;

        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        // 求图的联通分量
        for (int i = 0; i < G.V(); i++)
            if (visited[i] == -1) {
                bfs(i);
                cccount++;
            }

    }

    // 返回图的联通分量个数
    public int count() {
        return cccount;
    }

    // 显示visited数组内容
    public void showVisited() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
    }

    // 查询点v和点w是否联通
    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    // 查看联通分量
    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[cccount];

        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < G.V(); i++) {
            res[visited[i]].add(i);
        }

        return res;

    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testG.txt");
        Components components = new Components(g);
        components.showVisited();
        System.out.println(components.count());

        System.out.println(components.isConnected(0, 5));
        System.out.println(components.isConnected(0, 6));

        ArrayList<Integer>[] comp = components.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }

}