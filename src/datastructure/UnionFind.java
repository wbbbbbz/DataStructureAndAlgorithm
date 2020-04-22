package datastructure;

public interface UnionFind {

    // p和q可以作为索引
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}