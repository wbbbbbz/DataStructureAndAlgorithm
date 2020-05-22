package datastructure;

import java.util.ArrayList;
import java.util.Vector;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    public static final boolean DIRECTED = true;
    public static final boolean UNDIRECTED = false;
    // vertexes: 点数，edges: 边数
    private int vertexes, edges;
    // true: 有向图
    private boolean isDirected;
    // 邻接矩阵: true代表连接
    private Edge<Weight>[][] graph;

    public DenseWeightedGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new Edge[vertexes][vertexes];
        for (int i = 0; i < vertexes; i++)
            for (int j = 0; j < vertexes; j++)
                graph[i][j] = null;
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

        if (hasEdge(e.v(), e.w()))
            return;

        graph[e.v()][e.w()] = new Edge(e);
        // 无向图的话，两边都要修改
        // v == w: 自环边，不考虑
        if (e.v() != e.w() && !isDirected)
            graph[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());

        edges++;
    }

    public boolean hasEdge(int v, int w) {
        boundsCheck(v);
        boundsCheck(w);
        return graph[v][w] != null;
    }

    private void boundsCheck(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Edge<Weight>> adj(int v) {
        boundsCheck(v);
        Vector<Edge<Weight>> adjV = new Vector<>();
        for (int i = 0; i < vertexes; i++)
            if (graph[v][i] != null)
                adjV.add(graph[v][i]);
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
            System.out.print("vertex " + String.format("%02d", i) + ": ");
            for (int j = 0; j < vertexes; j++)
                System.out.print((graph[i][j] == null ? 0 : graph[i][j].wt()) + "\t");
            System.out.println();
        }

    }

    public void validateVertex(int v) {
        if (v < 0 || v >= vertexes)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    @Override
    public int degree(int v) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        graph[v][w] = null;
        edges--;

        if (!isDirected) {

            graph[w][v] = null;
            edges--;
        }

    }

    @Override
    public Object clone() {

        // TODO
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }
}