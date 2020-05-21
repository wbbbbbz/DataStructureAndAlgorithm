package algorithm.graph;

import java.util.Vector;

import datastructure.Edge;
import datastructure.IndexMinHeap;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;
import datastructure.WeightedGraph;

// 使用优化的Prim算法求图的最小生成树
public class PrimMST2<Weight extends Number & Comparable> {

    private WeightedGraph G; // 图的引用
    private IndexMinHeap<Weight> ipq; // 最小索引堆, 算法辅助数据结构
    private Edge<Weight>[] edgeTo; // 访问的点所对应的边, 算法辅助数据结构。存储的是最短的横切边。
    private boolean[] marked; // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst; // 最小生成树所包含的所有边
    private Number mstWeight; // 最小生成树的权值

    // 构造函数, 使用Prim算法求图的最小生成树
    public PrimMST2(WeightedGraph graph) {

        G = graph;
        assert (graph.E() >= 1);
        ipq = new IndexMinHeap<Weight>(graph.V());

        // 算法初始化
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new Vector<Edge<Weight>>();

        marked[0] = true;
        for (int i = 1; i < G.V(); i++) {
            Edge<Weight> minEdge = null;
            for (int v = 0; v < G.V(); v++) {
                if (marked[v]) {
                    for (Object item : G.adj(v)) {
                        Edge<Weight> e = (Edge<Weight>) item;
                        if (minEdge == null)
                            minEdge = e;
                        int w = e.other(v);
                        if (!marked[w] && e.wt().compareTo(minEdge.wt()) < 0) {
                            minEdge = e;
                        }
                    }
                }
            }
            mst.add(minEdge);
            marked[minEdge.v()] = true;
            marked[minEdge.w()] = true;
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++)
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    // 返回最小生成树的权值
    Number result() {
        return mstWeight;
    }

    // 测试 Prim
    public static void main(String[] args) {

        String filename = "testfiles\\testWG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST2<Double> primMST = new PrimMST2<Double>(g);
        Vector<Edge<Double>> mst = primMST.mstEdges();
        for (int i = 0; i < mst.size(); i++)
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }
}