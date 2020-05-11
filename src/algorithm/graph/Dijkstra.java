package algorithm.graph;

import java.util.Vector;

import datastructure.Edge;
import datastructure.IndexMinHeap;
import datastructure.WeightedGraph;

import java.util.Stack;

public class Dijkstra<Weight extends Number & Comparable> {

    private WeightedGraph G; // 图的引用
    private int s; // 起始点
    private Number[] distTo; // distTo[i]存储从起始点s到i的最短路径长度
    private boolean[] marked; // 标记数组, 在算法运行过程中标记节点i是否被访问，标记为true的就是在最短路径上被确定的节点了
    private Edge<Weight>[] from; // from[i]记录最短路径中, 到达i点的边是哪一条
                                 // 可以用来恢复整个最短路径

    public Dijkstra(WeightedGraph graph, int s) {
        // 初始化
        this.G = graph;
        this.s = s;
        distTo = new Number[G.V()];
        marked = new boolean[G.V()];
        from = new Edge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }

        // 使用最小索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(G.V());

        // 对起始点s进行初始化
        distTo[s] = 0.0;
        from[s] = new Edge(s, s, (Weight) (Number) 0.0);
        ipq.insert(s, (Weight) distTo[s]);
        marked[s] = true;
        while (!ipq.isEmpty()) {
            // 取出现在的最短路径上最新的节点
            // 因为是索引堆，所以v就是节点
            int v = ipq.extractMinIndex();

            // distTo[v]就是s到v的最短距离
            marked[v] = true;

            // 对所有v相邻节点进行更新
            for (Object item : G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) item;
                int w = e.other(v);
                // 如果w点的最短路径还没有被找到
                if (!marked[w]) {
                    // 需要更新的情况是
                    // 1. 没有值(之前无法到达)
                    // 2. 更短的路径可能了
                    if (from[w] == null || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue()) {
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;
                        if (ipq.contain(w))
                            ipq.change(w, (Weight) distTo[w]);
                        else
                            ipq.insert(w, (Weight) distTo[w]);
                    }
                }
            }
        }
    }

    // 返回从s点到w点的最短路径长度
    public Number shortestPathTo(int w) {
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        return distTo[w];
    }

    // 判断从s点到w点是否联通
    public boolean hasPathTo(int w) {
        assert w >= 0 && w < G.V();
        return marked[w];
    }

    // 寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
    public Vector<Edge<Weight>> shortestPath(int w) {

        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        // 通过from数组逆向查找到从s到w的路径, 存放到栈中
        Stack<Edge<Weight>> s = new Stack<Edge<Weight>>();
        Edge<Weight> e = from[w];
        while (e.v() != this.s) {
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        // 从栈中依次取出元素, 获得顺序的从s到w的路径
        Vector<Edge<Weight>> res = new Vector<Edge<Weight>>();
        while (!s.empty()) {
            e = s.pop();
            res.add(e);
        }

        return res;
    }

    // 打印出从s点到w点的路径
    public void showPath(int w) {

        assert w >= 0 && w < G.V();
        assert hasPathTo(w);

        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + " -> ");
            if (i == path.size() - 1)
                System.out.println(path.elementAt(i).w());
        }
    }

}