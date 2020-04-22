package datastructure;

public class UnionFindQuickUnion implements UnionFind {

    private int[] parent;

    public UnionFindQuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i; // 这样所有元素组别不一样
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // p和q所属的集合进行并集处理
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
    }
}