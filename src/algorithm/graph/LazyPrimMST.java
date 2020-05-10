package algorithm.graph;

import java.util.Vector;

import datastructure.Edge;
import datastructure.MinHeap;
import datastructure.WeightedGraph;

public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> G; // 图的引用
    private MinHeap<Edge<Weight>> pq; // 最小堆, 算法辅助数据结构
    private boolean[] marked; // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst; // 最小生成树所包含的所有边
    private Number mstWeight; // 最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> graph) {
        // 算法初始化
        G = graph;
        pq = new MinHeap<Edge<Weight>>(G.E());
        marked = new boolean[G.V()];
        mst = new Vector<Edge<Weight>>();

        // Lazy Prim
        visit(0);
        while (!pq.isEmpty()) {
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = pq.extractMin();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            // 因为只要在pq中，那么一定有一条边是true
            // 以marked来区分是不是横切边
            if (marked[e.v()] == marked[e.w()])
                continue;
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);

            // 访问和这条边连接的还没有被访问过的节点
            if (!marked[e.v()])
                visit(e.v());
            else
                visit(e.w());
            // System.out.println(pq);
            // System.out.println(mst);
        }

        // 计算最小生成树的权值

        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++)
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 访问节点v
    private void visit(int v) {

        assert !marked[v];
        marked[v] = true;

        // 将和节点v相连接的所有未访问的边放入最小堆中
        for (Edge<Weight> e : G.adj(v)) {
            // System.out.println(e);
            if (!marked[e.other(v)])
                pq.add(e);
            // System.out.println(pq);
        }
    }

    // 返回最小生成树的所有边
    public Vector<Edge<Weight>> mstEdges() {
        return mst;
    };

    // 返回最小生成树的权值
    public Number result() {
        return mstWeight;
    };

}