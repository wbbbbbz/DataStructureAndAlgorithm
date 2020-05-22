package datastructure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    public static final boolean DIRECTED = true;
    public static final boolean UNDIRECTED = false;
    // vertexes: 点数，edges: 边数
    private int vertexes, edges;
    // true: 有向图
    private boolean isDirected;
    // 邻接矩阵: true代表连接
    private TreeSet<Edge<Weight>>[] graph;

    public SparseWeightedGraph(int vertexes, boolean isDirected) {
        this.vertexes = vertexes;
        this.edges = 0;
        this.isDirected = isDirected;
        this.graph = new TreeSet[vertexes];
        for (int i = 0; i < vertexes; i++)
            graph[i] = new TreeSet<Edge<Weight>>();
    }

    public SparseWeightedGraph(int vertexes) {
        this(vertexes, false);
    }

    public SparseWeightedGraph(String filename) {
        this(filename, false);
    }

    public SparseWeightedGraph(String filename, boolean directed) {
        File file = new File(filename);
        this.isDirected = directed;
        try (Scanner scanner = new Scanner(file)) {
            vertexes = scanner.nextInt();
            if (vertexes < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            graph = new TreeSet[vertexes];
            for (int i = 0; i < vertexes; i++)
                graph[i] = new TreeSet<>();

            edges = scanner.nextInt();
            if (edges < 0)
                throw new IllegalArgumentException("E must be non-negative!");
            for (int i = 0; i < edges; i++) {
                int v = scanner.nextInt();
                validateVertex(v);
                int w = scanner.nextInt();
                validateVertex(w);
                Number weight = null;
                if (scanner.hasNextDouble()) {
                    weight = scanner.nextDouble();
                } else if (scanner.hasNextInt()) {
                    weight = scanner.nextInt();
                }

                // 检测自环边和平行边
                if (v == w)
                    throw new IllegalArgumentException("Self Loop is Detected");
                // if (graph[v].contains(w))
                // throw new IllegalArgumentException("Parallel Edge is Detected");
                graph[v].add(new Edge(v, w, weight));
                if (!isDirected)
                    graph[w].add(new Edge(w, v, weight));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        graph[e.v()].add(new Edge(e));
        // 无向图的话，两边都要修改
        if (e.v() != e.w() && !isDirected)
            graph[e.w()].add(new Edge(e.w(), e.v(), e.wt()));

        edges++;
    }

    // 传入v和w两个顶点
    public void addEdge(int v, int w, Weight weight) {
        this.addEdge(new Edge(v, w, weight));
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
        return graph[v];
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

    @Override
    public int degree(int v) {
        validateVertex(v);
        return graph[v].size();
    }

    @Override
    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        ArrayList<Edge<Weight>> removeEdges = new ArrayList<>();

        for (Edge<Weight> edge : adj(v)) {
            if (edge.w() == w) {
                removeEdges.add(edge);
            }
        }

        for (Edge<Weight> edge : removeEdges) {
            graph[v].remove(edge);
            edges--;
        }

        if (!isDirected) {

            removeEdges = new ArrayList<>();
            for (Edge<Weight> edge : adj(w)) {
                if (edge.w() == v) {
                    removeEdges.add(edge);
                }
            }

            for (Edge<Weight> edge : removeEdges) {
                graph[w].remove(edge);
                edges--;
            }
        }

    }

    @Override
    public boolean isDirected() {
        return this.isDirected;
    }

    @Override
    public Object clone() {

        try {
            SparseWeightedGraph cloned = (SparseWeightedGraph) super.clone();
            cloned.graph = new TreeSet[vertexes];
            for (int i = 0; i < vertexes; i++) {
                cloned.graph[i] = new TreeSet<Edge<Weight>>();
                for (Edge<Weight> edge : this.adj(i)) {
                    cloned.graph[i].add(edge.clone());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public Weight getWeight(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        for (Edge<Weight> edge : graph[v]) {
            if (edge.other(v) == w)
                return edge.wt();
        }
        return null;
    }

    public void setWeight(int v, int w, Weight weight) {
        validateVertex(v);
        validateVertex(w);
        for (Edge<Weight> edge : graph[v]) {
            if (edge.other(v) == w)
                edge.setWeight(weight);
        }
    }

}