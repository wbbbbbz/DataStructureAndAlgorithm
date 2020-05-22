package datastructure;

public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    public static final boolean DIRECTED = true;
    public static final boolean UNDIRECTED = false;
    // vertexes: 点数，edges: 边数
    private int vertexes, edges;
    // true: 有向图
    private boolean isDirected;
    // 邻接矩阵: true代表连接
    private LinkedList<Edge<Weight>>[] graph;

    public SparseWeightedGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new LinkedList[vertexes];
        for (int i = 0; i < vertexes; i++)
            graph[i] = new LinkedList<Edge<Weight>>();
    }

    public int getVertexes() {
        return this.vertexes;
    }

    public int getEdges() {
        return this.edges;
    }

    // 传入v和w两个顶点
    public void addEdge(Edge e) {
        boundsCheck(e.v());
        boundsCheck(e.w());

        // 允许平行边
        graph[e.v()].addFirst(new Edge(e));
        // 无向图的话，两边都要修改
        if (e.v() != e.w() && !isDirected)
            graph[e.w()].addFirst(new Edge(e.w(), e.v(), e.wt()));

        edges++;
    }

    public boolean hasEdge(int v, int w) {
        boundsCheck(v);
        boundsCheck(w);
        for (Edge<Weight> edge : adj(v)) {
            if (edge.w() == w)
                return true;
        }
        return false;
    }

    private void boundsCheck(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Edge<Weight>> adj(int v) {
        boundsCheck(v);
        return graph[v].iterable();
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
            System.out.print("vertex " + String.format("%02d", i) + ": ");
            for (Edge<Weight> e : adj(i))
                System.out.print("( to: " + e.other(i) + ", wt: " + e.wt() + "), ");
            System.out.println();
        }

    }

    public void validateVertex(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

}