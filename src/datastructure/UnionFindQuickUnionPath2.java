package datastructure;

public class UnionFindQuickUnionPath2 implements UnionFind {

    private int[] parent;
    private int[] size; // size[i]表示以i为根的集合中元素个数

    public UnionFindQuickUnionPath2(int size) {
        parent = new int[size];
        this.size = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i; // 这样所有元素组别不一样
            this.size[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 递归思路：find是返回p索引的根节点。
        // 所以如果现在p索引的父节点不是根节点，那就让父节点等于父节点的根节点即可

        if (parent[p] != p)
            parent[p] = find(parent[p]);
        return parent[p];
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

        // 根据树的元素大小进行判断
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }
}