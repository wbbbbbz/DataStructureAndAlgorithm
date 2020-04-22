package datastructure;

public class SegmentTree<E> {

    // 用户传入数据的副本
    private E[] data;
    private E[] tree;
    // 两个节点融合的方法
    private Merger<E> merger;

    public SegmentTree(E[] array, Merger<E> merger) {
        data = (E[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
        this.merger = merger;

        tree = (E[]) new Object[4 * array.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex处通过递归函数创建表示数据区间[l...r]的节点
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归基本条件：当l==r时创建叶子节点
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        // 递归递推条件，当l<r的时候，创建非叶子节点。
        // 此时该节点由左右节点组成（此处为加）
        // 因为已有getLeftChild和getRightChild，所以直接调用即可
        // data的l和r就需要思考一下。
        // 分割成：l, (l+r)/2, (l+r)/2+1, r

        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);
        int mid = getMid(l, r);
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int getMid(int l, int r) {
        return l + (r - l) / 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 使用递归
        // 1.写出递归的基本条件（l, r和queryL,queryR一致时返回值）
        // 2.递归的递推条件
        // 不一样的时候，就需要继续查询，使用同一个mid方法比较好！
        // l, mid和mid+1, r和queryL, mid 和 mid+1, queryR
        // 并且merge

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = getMid(l, r);
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);
        if (queryL > mid)
            return query(rightChild, mid + 1, r, queryL, queryR);
        if (queryR < mid + 1)
            return query(leftChild, l, mid, queryL, queryR);
        return merger.merge(query(leftChild, l, mid, queryL, mid), query(rightChild, mid + 1, r, mid + 1, queryR));
    }

    // 将index位置的元素更新为e
    public void set(int index, E e) {
        // 情况1. index超出数组范围
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        data[index] = e;
        set(0, 0, getSize() - 1, index, e);
    }

    // 对树的treeIndex（对应[l....r]区间）进行更改
    // 如果被区间包含，则由更新了的左或右节点重新merge
    private void set(int treeIndex, int l, int r, int index, E e) {
        // 1.递归基本条件：如果l==r==index,也就是对应的节点的时候，直接进行值的更新
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        // 2.递归递推条件。判断index是在区间的[l, mid]还是[mid+1, r]
        int mid = getMid(l, r);
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);
        if (index <= mid)
            set(leftChild, l, mid, index, e);
        else
            set(rightChild, mid + 1, r, index, e);
        tree[treeIndex] = merger.merge(tree[leftChild], tree[rightChild]);

    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        // 情况1. index超出数组范围
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int getRightChild(int index) {
        return index * 2 + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}