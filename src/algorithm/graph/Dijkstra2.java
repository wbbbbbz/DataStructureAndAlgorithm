package algorithm.graph;

import java.util.Vector;

import datastructure.Edge;
import datastructure.IndexMinHeap;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;
import datastructure.WeightedGraph;

import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra2<Weight extends Number & Comparable> {

    private WeightedGraph G; // 图的引用
    private int s; // 起始点
    private Number[] distTo; // distTo[i]存储从起始点s到i的最短路径长度
    private boolean[] marked; // 标记数组, 在算法运行过程中标记节点i是否被访问，标记为true的就是在最短路径上被确定的节点了
    private Edge<Weight>[] from; // from[i]记录最短路径中, 到达i点的边是哪一条
                                 // 可以用来恢复整个最短路径

    private class Node<Weight extends Number & Comparable> implements Comparable {
        public int v;
        public Weight dist;

        public Node(int v, Weight dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Object o) {
            Node<Weight> other = (Node<Weight>) o;
            return this.dist.compareTo(other.dist);
        }

    }

    public Dijkstra2(WeightedGraph graph, int s) {
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

        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 使用最小索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(G.V());

        // 对起始点s进行初始化
        distTo[s] = 0.0;
        from[s] = new Edge(s, s, (Weight) (Number) 0.0);

        pq.add(new Node(s, distTo[s]));

        ipq.insert(s, (Weight) distTo[s]);

        while (!pq.isEmpty()) {
            int v = pq.remove().v;
            if (marked[v])
                continue;
            marked[v] = true;
            for (Object item : G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) item;
                int w = e.other(v);
                if (!marked[w] && (from[w] == null
                        || distTo[v].doubleValue() + e.wt().doubleValue() < distTo[w].doubleValue())) {
                    distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                    from[w] = e;
                    pq.add(new Node(w, distTo[w]));
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

    public static void main(String[] args) {
        String filename = "testfiles\\testWG5.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
        // SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Dijkstra:\n");
        Dijkstra2<Integer> dij = new Dijkstra2<Integer>(g, 0);
        for (int i = 1; i < V; i++) {
            if (dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            } else
                System.out.println("No Path to " + i);

            System.out.println("----------");
        }
    }

}