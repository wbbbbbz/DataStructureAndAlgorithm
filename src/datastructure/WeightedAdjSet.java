package datastructure;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class WeightedAdjSet implements Cloneable {

    private int V, E; // V为顶点数，E为边数
    private TreeMap<Integer, Integer>[] adj; // 邻接集合

    public WeightedAdjSet(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new TreeMap[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeMap<>();

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                validateVertex(v);
                int w = scanner.nextInt();
                validateVertex(w);
                int weight = scanner.nextInt();

                // 检测自环边和平行边
                if (v == w)
                    throw new IllegalArgumentException("Self Loop is Detected");
                if (adj[v].containsKey(w)) {
                    adj[v].put(w, Math.min(adj[v].get(w), weight));
                    adj[w].put(v, Math.min(adj[w].get(v), weight));
                }
                adj[v].put(w, weight);
                adj[w].put(v, weight);
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
            for (Entry<Integer, Integer> entry : adj[v].entrySet())
                sb.append(String.format("(%d: %d)", entry.getKey(), entry.getValue()));
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
        return adj[v].containsKey(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v, int w) {
        if (hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    public int degree(int v) {
        // 在adj中已经检查了！
        validateVertex(v);
        return adj[v].size();
    }

    public void addEdge(int v, int w, int weight) {
        if (adj[v].containsKey(w))
            throw new IllegalArgumentException("Parallel Edge is Detected");
        if (v == w)
            throw new IllegalArgumentException("Self Loop is Detected");
        adj[v].put(w, weight);
        adj[w].put(v, weight);
    }

    public void show() {
        System.out.println(toString());
    }

    public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        adj[v].remove(w);
        adj[w].remove(v);

    }

    public Object clone() {

        try {
            WeightedAdjSet cloned = (WeightedAdjSet) super.clone();
            cloned.adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                cloned.adj[i] = new TreeMap<Integer, Integer>();
                for (Entry<Integer, Integer> entry : adj[i].entrySet()) {
                    cloned.adj[i].put(entry.getKey(), entry.getValue());
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {

        WeightedAdjSet weightedAdjSet = new WeightedAdjSet("testfiles\\testIntWG1.txt");
        System.out.print(weightedAdjSet);
    }
}