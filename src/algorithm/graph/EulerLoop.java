package algorithm.graph;

import java.util.ArrayList;
import java.util.Stack;

import datastructure.AdjSet;
import datastructure.Graph;
import datastructure.SparseGraph;

public class EulerLoop {

    private Graph G;
    // private boolean[] visited;
    private boolean hasEulerLoop;
    private ArrayList<Integer> eulerLoop;

    public EulerLoop(Graph G) {
        this.G = G;
        // this.visited = new boolean[G.V()];
        Components components = new Components(G);

        hasEulerLoop = (components.count() == 1);

        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 == 1)
                hasEulerLoop = false;
        }

        eulerLoop = new ArrayList<Integer>();

        if (hasEulerLoop) {
            Stack<Integer> curPath = new Stack<Integer>();
            Graph g = (Graph) G.clone();
            int curv = 0;
            // 1. 如果还有边(edges[i][j] == true)，那么就压栈
            // 2. 如果没有边，就弹栈，记录结果
            // 3. 如果栈空，那么就结束
            curPath.push(curv);
            while (!curPath.isEmpty()) {
                if (g.degree(curv) != 0) {
                    curPath.push(curv);
                    int w = g.adj(curv).iterator().next();
                    g.removeEdge(curv, w);
                    // System.out.println(curv + " " + w);
                    curv = w;
                } else {
                    eulerLoop.add(curv);
                    curv = curPath.pop();
                }
            }
        }

    }

    // private boolean isAllVisited() {
    // for (int i = 0; i < visited.length; i++) {
    // if (!visited[i])
    // return false;
    // }
    // return true;
    // }

    // // 深度优先递归
    // // 每一个顶点的度数是否是偶数
    // private boolean dfs(int v) {
    // visited[v] = true;

    // boolean isEven = true;
    // for (Integer w : G.adj(v)) {
    // isEven = !isEven;
    // if (!visited[w])
    // dfs(w);
    // }
    // return isEven;
    // }

    public boolean hasEulerLoop() {
        return hasEulerLoop;
    }

    public ArrayList<Integer> eulerLoop() {
        return eulerLoop;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("testfiles\\testEulerLoop2.txt");
        EulerLoop eulerLoop = new EulerLoop(g);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.eulerLoop());

        Graph g2 = new SparseGraph("testfiles\\testEulerLoop2.txt");
        eulerLoop = new EulerLoop(g2);
        System.out.println(eulerLoop.hasEulerLoop());
        System.out.println(eulerLoop.eulerLoop());
    }

}