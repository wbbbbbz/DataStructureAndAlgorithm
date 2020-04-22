package datastructure.test;

import java.util.Random;

import datastructure.UnionFind;
import datastructure.UnionFindQuickFind;
import datastructure.UnionFindQuickUnion;
import datastructure.UnionFindQuickUnionPath;
import datastructure.UnionFindQuickUnionRank;
import datastructure.UnionFindQuickUnionSize;

public class UnionFindTest {

    public static void main(String[] args) {

        // UnionFind1 慢于 UnionFind2
        // int size = 100000;
        // int m = 10000;

        // UnionFind2 慢于 UnionFind1, 但UnionFind3最快
        int size = 10000000;
        int m = 10000000;

        // UnionFindQuickFind uf1 = new UnionFindQuickFind(size);
        // System.out.println("UnionFind1 : " + testUF(uf1, m) + " s");

        // UnionFindQuickUnion uf2 = new UnionFindQuickUnion(size);
        // System.out.println("UnionFind2 : " + testUF(uf2, m) + " s");

        UnionFindQuickUnionSize uf3 = new UnionFindQuickUnionSize(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) + " s");

        UnionFindQuickUnionRank uf4 = new UnionFindQuickUnionRank(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) + " s");

        UnionFindQuickUnionPath uf5 = new UnionFindQuickUnionPath(size);
        System.out.println("UnionFind5 : " + testUF(uf5, m) + " s");

        UnionFindQuickUnionPath uf6 = new UnionFindQuickUnionPath(size);
        System.out.println("UnionFind6 : " + testUF(uf6, m) + " s");
    }

    private static double testUF(UnionFind uf, int m) {

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}