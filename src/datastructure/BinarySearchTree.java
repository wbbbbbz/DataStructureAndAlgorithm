package datastructure;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++; // 维护size!
            return new Node(e);
        }
        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (e.compareTo(node.e) > 0)
            return contains(node.right, e);
        if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        return true;
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 前序遍历非递归写法
    public void preOrderNR() {
        Stack<Node> nodes = new ArrayStack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();
            System.out.println(cur.e);
            if (cur.right != null)
                nodes.push(cur.right);
            if (cur.left != null)
                nodes.push(cur.left);
        }
    }

    // 层序遍历
    public void levelOrder() {

        Queue<Node> level = new LoopQueue<>();
        level.enqueue(root);
        while (!level.isEmpty()) {
            Node node = level.dequeue();
            System.out.println(node.e);
            if (node.left != null)
                level.enqueue(node.left);
            if (node.right != null)
                level.enqueue(node.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("Empty BST!");
        Node node = root;
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node.e;
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("Empty BST!");
        Node node = root;
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node.e;
    }

    // 删除二分搜索树的最小元素
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中最小节点
    // 返回删除节点后新的二分搜索数的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除二分搜索树的最小元素
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中最小节点
    // 返回删除节点后新的二分搜索数的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }
        node.right = removeMin(node.right);
        return node;
    }

    // 寻找特定的节点
    private Node find(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) > 0)
            return find(node.right, e);
        if (e.compareTo(node.e) < 0)
            return find(node.left, e);
        return node;
    }

    // 删除特定的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        Node successor = removeMin(node.right);
        successor.right = node.right;
        successor.left = node.left;
        node.right = null;
        node.left = null;
        return successor;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

}