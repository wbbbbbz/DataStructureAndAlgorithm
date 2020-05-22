package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import datastructure.Graph;
import datastructure.SparseGraph;

public class Hungarian {

    private Graph G;
    private int maxMatching = 0;
    private int[] matching;
    private boolean[] visited;

    public Hungarian(Graph G) {
        this.G = G;

        BipartitionDetection bd = new BipartitionDetection(G);
        assert (bd.isBipartite());

        this.G = G;

        // true是左侧，false是右侧
        boolean[] colors = bd.colors();

        this.matching = new int[G.V()];
        visited = new boolean[G.V()];

        Arrays.fill(matching, -1);

        for (int i = 0; i < colors.length; i++) {
            if (colors[i] && matching[i] == -1) {
                Arrays.fill(visited, false);
                if (bfs(i))
                    maxMatching++;
            }
        }

    }

    private boolean dfs(int v) {

        visited[v] = true;
        for (int u : G.adj(v))
            if (!visited[u]) {
                visited[u] = true;
                // dfs的递归导致找到的瞬间，就会不断反转路径
                if (matching[u] == -1 || dfs(matching[u])) {
                    matching[u] = v;
                    matching[v] = u;
                    return true;
                }
            }
        return false;
    }

    private boolean bfs(int v) {

        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[G.V()];
        Arrays.fill(pre, -1);

        q.add(v);
        pre[v] = v;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int next : G.adj(cur))
                if (pre[next] == -1) {
                    if (matching[next] != -1) {
                        q.add(matching[next]);
                        pre[next] = cur;
                        pre[matching[next]] = next;
                    } else {
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
        }
        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end) {

        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(start);
        return res;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args) {

        String lineSeperator = "--------------------------------------------------------------------------------------";

        String filename = "testfiles\\testMaxMatching1.txt";
        Graph g = new SparseGraph(filename);
        System.out.println(lineSeperator);
        g.show();
        System.out.println(lineSeperator);

        Hungarian hungarian = new Hungarian(g);
        System.out.println(hungarian.maxMatching());
        System.out.println(hungarian.isPerfectMatching());

    }

}