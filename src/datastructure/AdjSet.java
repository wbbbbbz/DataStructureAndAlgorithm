package datastructure;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class AdjSet implements Graph {

    private int V, E; // V为顶点数，E为边数
    private TreeSet<Integer>[] adj; // 邻接集合

    public AdjSet(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeSet<>();

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                validateVertex(v);
                int w = scanner.nextInt();
                validateVertex(w);

                // 检测自环边和平行边
                if (v == w)
                    throw new IllegalArgumentException("Self Loop is Detected");
                if (adj[v].contains(w))
                    throw new IllegalArgumentException("Parallel Edge is Detected");
                adj[v].add(w);
                adj[w].add(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append("vertex " + String.format("%02d", v) + ":");
            for (int w : adj[v])
                sb.append(String.format("%3d", w));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];

    }

    public int degree(int v) {
        // 在adj中已经检查了！
        validateVertex(v);
        return adj[v].size();
    }

    public static void main(String[] args) {

        AdjSet adjSet = new AdjSet("testfiles\\testG1.txt");
        System.out.print(adjSet);
    }

    @Override
    public void addEdge(int v, int w) {
        if (adj[v].contains(w))
            throw new IllegalArgumentException("Parallel Edge is Detected");
        if (v == w)
            throw new IllegalArgumentException("Self Loop is Detected");
        adj[v].add(w);
        adj[w].add(v);
    }

    @Override
    public void show() {
        System.out.println(toString());
    }
}