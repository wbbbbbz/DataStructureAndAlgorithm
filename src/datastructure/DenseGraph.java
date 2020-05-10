package datastructure;

import java.util.Vector;

// 稠密图 - 邻接矩阵
// 直接去掉了平行边的概念了
// O(1)判断是否有边
// 不考虑自环边
public class DenseGraph implements Graph {

    public static final boolean DIRECTED = true;
    public static final boolean UNDIRECTED = false;
    // vertexes: 点数，edges: 边数
    private int vertexes, edges;
    // true: 有向图
    private boolean isDirected;
    // 邻接矩阵: true代表连接
    private boolean[][] graph;

    public DenseGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new boolean[vertexes][vertexes];
        for (int i = 0; i < vertexes; i++)
            graph[i] = new boolean[vertexes];
    }

    public int getVertexes() {
        return this.vertexes;
    }

    public int getEdges() {
        return this.edges;
    }

    // 传入v和w两个顶点
    public void addEdge(int v, int w) {
        boundsCheck(v);
        boundsCheck(w);

        if (hasEdge(v, w))
            return;

        graph[v][w] = true;
        // 无向图的话，两边都要修改
        // v == w: 自环边，不考虑
        if (v != w && !isDirected)
            graph[w][v] = true;

        edges++;
    }

    public boolean hasEdge(int v, int w) {
        boundsCheck(v);
        boundsCheck(w);
        return graph[v][w];
    }

    private void boundsCheck(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Integer> adj(int v) {
        boundsCheck(v);
        Vector<Integer> adjV = new Vector<Integer>();
        for (int i = 0; i < vertexes; i++)
            if (graph[v][i])
                adjV.add(i);
        return adjV;
    }

    @Override
    public int V() {
        return vertexes;
    }

    @Override
    public int E() {
        return edges;
    }

    @Override
    public void show() {
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++)
                System.out.print(graph[i][j] + "\t");
            System.out.println();
        }

    }

}