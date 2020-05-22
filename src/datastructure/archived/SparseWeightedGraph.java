package datastructure.archived;

import datastructure.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            graph = new LinkedList[vertexes];
            for (int i = 0; i < vertexes; i++)
                graph[i] = new LinkedList<>();

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
                graph[v].addFirst(new Edge(v, w, weight));
                if (!isDirected)
                    graph[w].addFirst(new Edge(w, v, weight));
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

    @Override
    public int degree(int v) {
        validateVertex(v);
        return graph[v].getSize();
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
            graph[v].removeElement(edge);
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
                graph[w].removeElement(edge);
                edges--;
            }
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