package algorithm.graph;

import datastructure.Graph;

// 求图的连通分量的算法类
public class Components {

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int ccount; // 记录联通分量个数
    private int[] id; // 每个节点所对应的联通分量标记

    // 图的深度优先遍历
    void dfs(int v) {

        visited[v] = true;
        id[v] = ccount;
        // 递归
        for (Integer i : G.adj(v)) {
            if (!visited[i])
                dfs(i);
        }
    }

    // 构造函数，求无权图的联通分量
    public Components(Graph graph) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        ccount = 0;

        for (int i = 0; i < id.length; i++) {
            visited[i] = false;
            id[i] = -1;
        }

        // 求图的联通分量
        for (int i = 0; i < G.V(); i++)
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }

    }

    // 返回图的联通分量个数
    public int count() {
        return ccount;
    }

    // 查询点v和点w是否联通
    public boolean isConnected(int v, int w) {
        boundsCheck(v);
        boundsCheck(w);
        return id[v] == id[w];
    }

    private void boundsCheck(int v) {
        if (v < 0 || v >= G.V())
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }
}