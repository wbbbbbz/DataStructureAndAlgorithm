package algorithm.graph;

import java.util.Vector;

import datastructure.Edge;
import datastructure.WeightedGraph;

import java.util.Stack;

// 使用BellmanFord算法求最短路径
public class BellmanFord<Weight extends Number & Comparable> {

    private WeightedGraph G; // 图的引用
    private int s; // 起始点
    private Number[] distTo; // distTo[i]存储从起始点s到i的最短路径长度
    Edge<Weight>[] from; // from[i]记录最短路径中, 到达i点的边是哪一条
                         // 可以用来恢复整个最短路径
    boolean hasNegativeCycle; // 标记图中是否有负权环

    // 构造函数, 使用BellmanFord算法求最短路径
    public BellmanFord(WeightedGraph graph, int s) {

        G = graph;
        this.s = s;
        distTo = new Number[G.V()];
        from = new Edge[G.V()];
        // 初始化所有的节点s都不可达, 由from数组来表示
        for (int i = 0; i < G.V(); i++) {
            from[i] = null;
            distTo[i] = 0.0;
        }
        // 对起点s的初始化
        distTo[s] = 0.0;
        from[s] = new Edge<Weight>(s, s, (Weight) (Number) 0.0);

        // Bellman-Ford的过程
        // 进行V-1次循环，每一次循环求出从起点到其余所有点，最多使用pass步可以到达的最短距离
        // 因为每一次循环之后，所有的点是最多使用pass步可以到达的节点
        for (int pass = 1; pass < G.V(); pass++) {
            // 每次循环中对所有的边进行一遍松弛操作
            // 遍历所有边的方式是先遍历所有的顶点, 然后遍历和所有顶点相邻的所有边
            for (int i = 0; i < G.V(); i++) {
                // 使用我们实现的邻边迭代器遍历和所有顶点相邻的所有边
                for (Object item : G.adj(i)) {
                    Edge<Weight> e = (Edge<Weight>) item;
                    // 对于每一个边首先判断e->v()可达
                    // 之后看如果e->w()以前没有到达过， 显然我们可以更新distTo[e->w()]
                    // 或者e->w()以前虽然到达过, 但是通过这个e我们可以获得一个更短的距离, 即可以进行一次松弛操作, 我们也可以更新distTo[e->w()]
                    if (from[e.v()] != null && (from[e.w()] == null
                            || distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue())) {
                        distTo[e.w()] = distTo[e.v()].doubleValue() + e.wt().doubleValue();
                        from[e.w()] = e;
                    }
                }
            }
        }

        hasNegativeCycle = detectNegativeCycle();
    }

    // 判断图中是否有负权环
    public boolean detectNegativeCycle() {

        for (int i = 0; i < G.V(); i++) {
            for (Object item : G.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                // 再松弛一次，如果还能减少就是有负权环
                if (from[e.v()] != null
                        && distTo[e.v()].doubleValue() + e.wt().doubleValue() < distTo[e.w()].doubleValue())
                    return true;
            }
        }

        return false;
    }

    // 返回图中是否有负权环
    public boolean negativeCycle() {
        return hasNegativeCycle;
    }

    // 返回从s点到w点的最短路径长度
    public Number shortestPathTo(int w) {
        assert w >= 0 && w < G.V();
        assert !hasNegativeCycle;
        assert hasPathTo(w);
        return distTo[w];
    }

    // 判断从s点到w点是否联通
    public boolean hasPathTo(int w) {
        assert (w >= 0 && w < G.V());
        return from[w] != null;
    }

    // 寻找从s到w的最短路径, 将整个路径经过的边存放在vec中
    public Vector<Edge<Weight>> shortestPath(int w) {

        assert w >= 0 && w < G.V();
        assert !hasNegativeCycle;
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

        assert (w >= 0 && w < G.V());
        assert (!hasNegativeCycle);
        assert (hasPathTo(w));

        Vector<Edge<Weight>> res = shortestPath(w);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.elementAt(i).v() + " -> ");
            if (i == res.size() - 1)
                System.out.println(res.elementAt(i).w());
        }
    }
}