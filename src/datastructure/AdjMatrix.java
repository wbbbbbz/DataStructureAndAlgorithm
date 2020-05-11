package datastructure;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// 邻接矩阵的数据结构类，用于图
public class AdjMatrix {

    private int V, E; // V为顶点数，E为边数
    private int[][] adj; // 邻接矩阵

    public AdjMatrix(String filename) {
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new int[V][V];

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
                if (adj[v][w] == 1)
                    throw new IllegalArgumentException("Parallel Edge is Detected");
                adj[v][w] = 1;
                adj[w][v] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            sb.append("vertex " + String.format("%02d", i) + ":");
            for (int j = 0; j < V; j++)
                sb.append(String.format("%2d", adj[i][j]));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        AdjMatrix adjMatrix = new AdjMatrix("testfiles\\testG1.txt");
        System.out.print(adjMatrix);
    }

}