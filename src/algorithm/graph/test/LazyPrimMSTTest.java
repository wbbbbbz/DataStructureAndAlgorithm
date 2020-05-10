package algorithm.graph.test;

import java.util.Vector;

import algorithm.graph.LazyPrimMST;
import datastructure.Edge;
import datastructure.ReadWeightedGraph;
import datastructure.SparseWeightedGraph;

public class LazyPrimMSTTest {
    private static final String LINE_SEPERATE = "--------------------------------------------------------------------------------------";

    public static void main(String[] args) {

        testLazyPrimMST();
    }

    private static void testLazyPrimMST() {

        String filename = "testfiles\\testWG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println(LINE_SEPERATE);
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0; i < mst.size(); i++)
            System.out.println(mst.elementAt(i));
        System.out.println("There are " + mst.size() + " edges.");
        System.out.println("The MST weight is: " + lazyPrimMST.result());
        System.out.println(LINE_SEPERATE);
    }
}