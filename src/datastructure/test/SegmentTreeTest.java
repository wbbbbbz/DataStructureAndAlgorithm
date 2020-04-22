package datastructure.test;

import datastructure.SegmentTree;

public class SegmentTreeTest {

    public static void main(String[] args) {
        testSegmentTree();
    }

    public static void testSegmentTree() {
        Integer[] nums = { -2, 0, 3, -5, 2, -1 };
        SegmentTree<Integer> intSegmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(intSegmentTree);
        System.out.println(intSegmentTree.query(0, 3));
    }

}