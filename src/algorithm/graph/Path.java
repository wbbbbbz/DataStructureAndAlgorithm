package algorithm.graph;

import java.util.Vector;

import datastructure.ArrayStack;
import datastructure.Graph;
import datastructure.Stack;

// 深度遍历方式求两点间的路径
public class Path {

    Graph G; // 图的引用
    private boolean[] visited; // 记录dfs的过程中节点是否被访问
    private int s; // 图的原点s
    private int[] from; // 保存了所有边的source的数组

    // 图的深度优先遍历
    void dfs(int v) {

        visited[v] = true;
        // 递归
        for (Integer i : G.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    // 构造函数，求无权图的路径
    public Path(Graph graph, int s) {

        // 初始化
        G = graph;
        visited = new boolean[G.V()];
        this.s = s;
        from = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        // 求图的路径
        dfs(s);

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

    private void boundsCheck(int v) {
        if (v < 0 || v >= G.V())
            throw new IllegalArgumentException("The parameter " + v + " is out of bounds!");
    }
}