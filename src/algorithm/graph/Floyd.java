package algorithm.graph;

import datastructure.Edge;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;
import datastructure.WeightedGraph;

// 使用Floyd算法求所有点对最短路径
public class Floyd<Weight extends Number & Comparable> {

    private WeightedGraph G; // 图的引用
    private Number[][] distTo; // distTo[i][j]存储从i到j的最短路径长度
    private boolean hasNegativeCycle; // 标记图中是否有负权环

    // 构造函数, 使用Floyd算法求所有点对最短路径
    public Floyd(WeightedGraph graph) {

        G = graph;
        distTo = new Number[G.V()][];
        // 初始化所有的节点s都不可达, 由from数组来表示
        // v->v = 0，v->w记录
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = new Number[G.V()];
            for (Object item : G.adj(v)) {
                Edge<Weight> e = (Edge<Weight>) item;
                int w = e.other(v);
                distTo[v][w] = e.wt();
            }
            for (int j = 0; j < G.V(); j++) {
                if (distTo[v][j] == null)
                    distTo[v][j] = Double.MAX_VALUE;
            }
            distTo[v][v] = 0.0;
        }

        // Floyd的过程
        // 三层V次循环
        // 第一层：绕道点
        for (int t = 0; t < G.V(); t++) {
            // 第二层：搜索最短距离的起始点v
            for (int v = 0; v < G.V(); v++) {
                // 第三层：搜索最短距离的终止点w
                for (int w = 0; w < G.V(); w++) {
                    if (distTo[v][t].doubleValue() + distTo[t][w].doubleValue() < distTo[v][w].doubleValue()) {
                        distTo[v][w] = distTo[v][t].doubleValue() + distTo[t][w].doubleValue();
                    }
                }
            }
        }
        hasNegativeCycle = detectNegativeCycle();
    }

    // 判断图中是否有负权环
    public boolean detectNegativeCycle() {

        for (int v = 0; v < G.V(); v++) {
            if (distTo[v][v].doubleValue() < 0.0)
                return true;
        }

        return false;
    }

    // 返回图中是否有负权环
    public boolean negativeCycle() {
        return hasNegativeCycle;
    }

    // 返回图中所有点对的最短路径
    public void showAllMinDistances() {
        System.out.println("Printing all min distances.");
        for (int v = 0; v < G.V(); v++) {
            System.out.print("Vertex " + v + ": ");
            for (int w = 0; w < G.V(); w++) {
                if (distTo[v][w].doubleValue() != Double.MAX_VALUE)
                    System.out.print(
                            "(" + v + " -> " + w + "): " + String.format("%.2f", distTo[v][w].doubleValue()) + "\t");
                else
                    System.out.print("(" + v + " -> " + w + "): null\t");
            }
            System.out.println();
        }
    }

    // 返回图中特定点对的最短路径
    public void showMinDistance(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        System.out.println("The min distance: (" + v + " -> " + w + "): " + distTo[v][w].doubleValue());
    }

    public static void main(String[] args) {
        String filename = "testfiles\\testWG7.txt";
        int V = 5;

        SparseWeightedGraph<Integer> g = new SparseWeightedGraph<Integer>(V, true);
        // Dijkstra最短路径算法同样适用于有向图
        // SparseGraph<int> g = SparseGraph<int>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        System.out.println("Test Floyd:\n");
        Floyd<Integer> floyd = new Floyd<Integer>(g);
        System.out.println(floyd.negativeCycle());
        floyd.showAllMinDistances();
    }

}