package datastructure;

public interface Graph extends Cloneable {

    public int V();

    public int E();

    public void addEdge(int v, int w);

    boolean hasEdge(int v, int w);

    void show();

    public Iterable<Integer> adj(int v);

    public int degree(int v);

    public void validateVertex(int v);

    public void removeEdge(int v, int w);

    public boolean isDirected();

    public Object clone();

    public default int indegree(int v) {
        return degree(v);
    };

    public default int outdegree(int v) {
        return degree(v);
    };

    public default Graph reverse() {
        throw new RuntimeException();
    };

}