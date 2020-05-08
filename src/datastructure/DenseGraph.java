package datastructure;

// 稠密图 - 邻接矩阵
// 直接去掉了平行边的概念了
// O(1)判断是否有边
public class DenseGraph {

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

        if (graph[v][w])
            return;

        graph[v][w] = true;
        // 无向图的话，两边都要修改
        if (!isDirected)
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

}