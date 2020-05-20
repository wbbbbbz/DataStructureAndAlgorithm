package algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class WaterPuzzle {

    private int xVolume, yVolume;
    private Queue<Integer> queue;
    private boolean[] visited;
    private int[] from;
    private int end = -1;

    public WaterPuzzle() {

        xVolume = 5;
        yVolume = 3;

        queue = new LinkedList<>();
        // 十进制两个数字的状态压缩最多只有100，其实54个空间也行
        visited = new boolean[100];
        from = new int[100];

        end = bfs();

    }

    private int bfs() {
        queue.add(0);
        visited[0] = true;
        from[0] = 0;

        while (!queue.isEmpty()) {
            int water = queue.poll();
            int x = water / 10;
            int y = water % 10;
            for (int next : next(x, y)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    from[next] = water;
                    if (next / 10 == 4 || next % 10 == 4) {
                        return next;
                    }
                }
            }
        }

        return -1;
    }

    private Iterable<Integer> next(int x, int y) {
        ArrayList<Integer> res = new ArrayList<>();
        // 不用担心状态重合，因为会通过visited判断！
        // 变化1：x桶清空
        res.add(y);
        // 变化2：y桶清空
        res.add(x * 10);
        // 变化3：x桶装满
        res.add(xVolume * 10 + y);
        // 变化4：y桶装满
        res.add(x * 10 + yVolume);
        // 变化5：x桶倒入y(通过求变化值的最小值也行！)
        if (x != 0 && y != yVolume) {
            if (x > yVolume - y)
                res.add((x - yVolume + y) * 10 + yVolume);
            else
                res.add(x + y);
        }
        // 变化6：y桶倒入x
        if (y != 0 && x != xVolume) {
            if (y > xVolume - x)
                res.add(xVolume * 10 + (y - xVolume + x));
            else
                res.add((x + y) * 10);
        }
        // System.out.println(res);
        return res;

    }

    public Iterable<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1)
            return res;

        int curr = end;
        res.add(end);
        while (from[curr] != curr) {
            curr = from[curr];
            res.add(curr);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new WaterPuzzle().result());
    }

}