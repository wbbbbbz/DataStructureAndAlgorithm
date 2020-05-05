package datastructure.test;

import datastructure.BinarySearchTree;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        binarySearchTreeTest();
    }

    public static void binarySearchTreeTest() {
        BinarySearchTree<Integer, Object> bst = new BinarySearchTree<>();
        // int[] nums = { 5, 3, 6, 8, 4, 2 };
        // for (int num : nums) {
        // bst.add(num);
        // }
        // bst.preOrder();
        // System.out.println();
        // bst.preOrderNR();
        // System.out.println();
        // bst.inOrder();
        // System.out.println();
        // bst.postOrder();
        // System.out.println();

        // System.out.println(bst);

        // bst.levelOrder();
        // System.out.println();

        // Random random = new Random();
        // int n = 1000;

        // for (int i = 0; i < n; i++) {
        // bst.add(random.nextInt(10000));
        // }
        // Array<Integer> nums = new Array<>();
        // while (!bst.isEmpty())
        // nums.addLast(bst.removeMin());
        // System.out.println(nums);

        int[] nums = { 5, 3, 6, 8, 4, 2 };
        for (int num : nums) {
            bst.add(num, null);
        }
        bst.remove(5);
        System.out.println(bst);

    }

}