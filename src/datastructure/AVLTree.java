package datastructure;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    private Node getNode(K key) {
        Node node = root;
        while (node != null) {
            if (key.compareTo(node.key) < 0)
                node = node.left;
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    // 获得节点node的高度
    // 不用每一次都要判断了
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value, null, null);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        return rebalance(node);
    }

    private Node rebalance(Node node) {
        if (node == null) {
            return null;
        }

        // 更新节点高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        int leftBalanceFactor = getBalanceFactor(node.left);
        int rightBalanceFactor = getBalanceFactor(node.right);

        // 如果发生不平衡的话
        // 平衡维护
        // balanceFactor>1:左子树高度太高
        // leftBalanceFactor>=0:左子树的左子树太高，也就是插入的节点在左侧的左侧。向左倾斜
        // LL:右旋转
        if (balanceFactor > 1 && leftBalanceFactor >= 0)
            return rightRotate(node);
        // RR:左旋转
        if (balanceFactor < -1 && rightBalanceFactor <= 0)
            return leftRotate(node);
        // LR:先左旋再右旋
        if (balanceFactor > 1 && leftBalanceFactor < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL:先右旋再左旋
        if (balanceFactor < -1 && rightBalanceFactor > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 左旋转
    // 返回左旋转后的根节点
    private Node leftRotate(Node node) {
        Node x = node.right;
        Node xLeft = x.left;

        // 左旋转
        x.left = node;
        node.right = xLeft;

        // 更新height！
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 右旋转:
    // 返回右旋转后的根节点
    private Node rightRotate(Node node) {
        Node x = node.left;
        Node xRight = x.right;

        // 右旋转
        x.right = node;
        node.left = xRight;

        // 更新height！
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 计算平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        // ArrayList<K> keys = new ArrayList<>();
        // // 中序遍历是按顺序排列的
        // inOrder(root, keys);
        // for (int i = 1; i < keys.size(); i++) {
        // if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
        // return false;
        // }
        // }
        // return true;
        return isBST(root);
    }

    // 返回以node为根的子树是否是BST
    private boolean isBST(Node node) {
        // 1.递推的基本条件
        if (node == null)
            return true;

        boolean res = true;
        if (node.left != null && node.right != null)
            res = node.left.key.compareTo(node.right.key) < 0;
        return res && isBST(node.left) && isBST(node.right);
    }

    // 中序遍历
    // 递归
    private void inOrder(Node node, ArrayList<K> keys) {
        // 1.递归的基本条件
        if (node == null)
            return;

        // 2.递归的递推性质
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否平衡
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 平衡的条件是左右子树的高度差绝对值在1以内
    // 空节点一定是平衡的
    // 平衡条件：左子树平衡，右子树平衡，左右子树高度差绝对值在1以内
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        return isBalanced(node.left) && isBalanced(node.right) && Math.abs(getBalanceFactor(node)) <= 1;
    }

    public V remove(K key) {
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 维护删除后的节点的平衡！
    private Node remove(Node node, K key) {
        if (node == null)
            return node;

        // 不能太早的将节点返回，必须先进行维护才行！
        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else if (node.left == null) {
            Node delNode = node;
            node = node.right;
            delNode.right = null;
            size--;
            retNode = node;
        } else if (node.right == null) {
            Node delNode = node;
            node = node.left;
            delNode.left = null;
            size--;
            retNode = node;
        } else {
            Node successor = getMinimumNode(node.right);
            Node delNode = node;
            successor.right = remove(node.right, successor.key);
            successor.left = node.left;
            delNode.left = null;
            delNode.right = null;
            retNode = successor;
        }

        return rebalance(retNode);

    }

    private Node getMinimumNode(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node == null)
            return null;
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        size--;
        return node;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + " does not exist!");
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}