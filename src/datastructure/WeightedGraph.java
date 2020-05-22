package datastructure;

public interface WeightedGraph<Weight extends Number & Comparable> extends Cloneable {
    public int V();

    public int E();

    public void addEdge(Edge<Weight> e);

    boolean hasEdge(int v, int w);

    void show();

    public Iterable<Edge<Weight>> adj(int v);

    public void validateVertex(int v);

    public int degree(int v);

    public void removeEdge(int v, int w);

    public boolean isDirected();

    public Object clone();

}