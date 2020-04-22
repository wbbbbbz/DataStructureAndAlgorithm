package datastructure;

public class UnionFindQuickFind implements UnionFind {

    private int[] id;

    public UnionFindQuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++)
            id[i] = i; // 这样所有元素组别不一样
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素p所对应的集合编号
    private int find(int p) {
        if (p < 0 && p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // p和q所属的集合进行并集处理
    @Override
    public void unionElements(int p, int q) {
        int pIndex = find(p);
        int qIndex = find(q);

        if (pIndex == qIndex)
            return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qIndex)
                id[i] = pIndex;
        }
    }

}