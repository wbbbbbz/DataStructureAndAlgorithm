package datastructure;

public class UnionFindQuickUnionRank implements UnionFind {

    private int[] parent;
    private int[] rank; // rank[i]表示以i为根的集合中元素深度

    public UnionFindQuickUnionRank(int size) {
        parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i; // 这样所有元素组别不一样
            this.rank[i] = 1;
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

        // 根据树的深度大小进行判断
        // 深度小的树指向深度大的树
        // 需要维护深度其实只有两棵树深度相等的时候！
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}