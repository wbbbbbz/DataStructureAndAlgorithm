package algorithm.graph;

import java.util.Vector;

import datastructure.ArrayStack;
import datastructure.Graph;
import datastructure.LoopQueue;
import datastructure.Queue;
import datastructure.Stack;

public class ShortestPath {

    private Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int s; // 图的原点s
    private int[] from; // 保存了所有边的source的数组
    private int[] ord; // order数组，记录s到每一个节点最短距离是多少

    // 构造函数，求无权图的路径
    public ShortestPath(Graph graph, int s) {

        // 初始化
        G = graph;
        boundsCheck(s);
        visited = new boolean[G.V()];
        this.s = s;
        from = new int[G.V()];
        ord = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        bfs(s);

    }

    private void bfs(int s) {
        // 求图的路径
        Queue<Integer> queue = new LoopQueue<>();
        queue.enqueue(s);
        visited[s] = true;
        ord[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer i : G.adj(v)) {
                if (!visited[i]) {
                    queue.enqueue(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    // 返回有没有路径能到达w
    public boolean hasPath(int w) {
        boundsCheck(w);
        return visited[w];
    }

    // 输出到达节点w的路径
    public void path(int w, Vector<Integer> vec) {
        boundsCheck(w);
        Stack<Integer> stack = new ArrayStack<>();
        while (w != -1) {
            stack.push(w);
            w = from[w];
        }
        while (!stack.isEmpty())
            vec.add(stack.pop());
    }

    // 打印到达节点w的路径
    public void showPath(int w) {
        Vector<Integer> vec = new Vector<>();
        path(w, vec);
        System.out.print("The path to " + w + " : ");
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.get(i));
            if (i + 1 != vec.size()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // 查询s到w的最短路径的长度
    public int length(int w) {
        boundsCheck(w);
        return ord[w];
    }

    private void boundsCheck(int v) {
        if (v < 0 || v >= G.V())
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }

}