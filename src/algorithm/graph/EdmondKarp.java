package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import datastructure.Edge;
import datastructure.SparseWeightedGraph;

public class EdmondKarp<Weight extends Number & Comparable> {

    private SparseWeightedGraph network;
    private SparseWeightedGraph residualGraph;
    private int s, t; // s是源点，t是汇点
    private int maxFlow = 0; // 最大流

    public EdmondKarp(SparseWeightedGraph network, int s, int t) {
        if (!network.isDirected())
            throw new IllegalArgumentException("Only works in directed graph.");
        if (network.V() < 2)
            throw new IllegalArgumentException("The network should has at least 2 points.");
        network.validateVertex(s);
        network.validateVertex(t);
        if (s == t)
            throw new IllegalArgumentException("s and t should be different");

        this.network = network;
        this.s = s;
        this.t = t;

        residualGraph = new SparseWeightedGraph<>(network.V(), true);
        for (int v = 0; v < network.V(); v++) {
            for (Object item : network.adj(v)) {
                Edge<Weight> edge = (Edge<Weight>) item;
                int w = edge.other(v);
                residualGraph.addEdge(edge);
                residualGraph.addEdge(new Edge(w, v, 0.0));
            }
        }
        // residualGraph.show();

        while (true) {

            ArrayList<Integer> augPath = getAugmentingPath();
            // System.out.println(augPath);
            if (augPath.size() == 0)
                break;
            Double f = Double.MAX_VALUE;
            // residualGraph.show();
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                // System.out.println(residualGraph.getWeight(v, w).doubleValue());
                f = Math.min(f, residualGraph.getWeight(v, w).doubleValue());
            }
            maxFlow += f;
            // 根据增广路径更新residualGraph
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                residualGraph.setWeight(v, w, residualGraph.getWeight(v, w).doubleValue() - f);
                residualGraph.setWeight(w, v, residualGraph.getWeight(v, w).doubleValue() + f);
            }
        }

    }

    // 获得增广路径
    private ArrayList<Integer> getAugmentingPath() {

        ArrayList<Integer> res = new ArrayList<Integer>();

        LinkedList<Integer> queue = new LinkedList<>();

        int[] from = new int[network.V()];
        Arrays.fill(from, -1);
        queue.add(s);
        from[s] = s;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Object item : residualGraph.adj(v)) {
                Edge<Weight> edge = (Edge<Weight>) item;
                int w = edge.other(v);
                if (from[w] == -1 && edge.wt().doubleValue() > 0.0) {
                    from[w] = v;
                    queue.add(w);
                    if (w == t) {
                        int curr = w;
                        res.add(curr);
                        while (curr != s) {
                            curr = from[curr];
                            res.add(curr);
                        }
                        Collections.reverse(res);
                        return res;
                    }
                }
            }
        }
        return res;
    }

    public int result() {
        return maxFlow;
    }

    public Number flow(int v, int w) {
        return (int) residualGraph.getWeight(w, v);
    }

    public static void main(String[] args) {

        SparseWeightedGraph<Double> g2 = new SparseWeightedGraph<>("testfiles\\testNetwork3.txt", true);
        EdmondKarp<Double> edmondKarp = new EdmondKarp<>(g2, 0, 10);
        System.out.print("MaxFlow: ");
        System.out.println(edmondKarp.result());
    }

}