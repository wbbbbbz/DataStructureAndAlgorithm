package datastructure;

public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {

    private int a, b; // 边的两个端点。有向边的话需要注意a与b的顺序
    private Weight weight; // 边的权值

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public Edge(Edge<Weight> e) {
        this.a = e.a;
        this.b = e.b;
        this.weight = e.weight;
    }

    public int v() {
        return a;
    } // 返回第一个顶点

    public int w() {
        return b;
    } // 返回第二个顶点

    public Weight wt() {
        return weight;
    } // 返回权值

    // 给定一个顶点, 返回另一个顶点
    public int other(int x) {
        assert x == a || x == b;
        return x == a ? b : a;
    }

    // 输出边的信息
    public String toString() {
        return "" + a + "-" + b + ": " + weight;
    }

    // 边之间的比较
    public int compareTo(Edge that) {
        return weight.compareTo(that.weight);
    }

}