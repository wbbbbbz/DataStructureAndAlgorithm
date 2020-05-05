package datastructure;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key) {
            this(key, null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
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

    // 向二分搜索树中添加新的元素key
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++; // 维护size!
            return new Node(key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }
        return node;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " does not exist!");
        node.value = newValue;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null)
            return false;
        if (key.compareTo(node.key) > 0)
            return contains(node.right, key);
        if (key.compareTo(node.key) < 0)
            return contains(node.left, key);
        return true;
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.println(node.key);
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
        System.out.println(node.key);
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
        System.out.println(node.key);
    }

    // 前序遍历非递归写法
    public void preOrderNR() {
        Stack<Node> nodes = new ArrayStack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            Node cur = nodes.pop();
            System.out.println(cur.key);
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
            System.out.println(node.key);
            if (node.left != null)
                level.enqueue(node.left);
            if (node.right != null)
                level.enqueue(node.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public K minimum() {
        if (size == 0)
            throw new IllegalArgumentException("Empty BST!");
        Node node = root;
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    // 寻找二分搜索树的最大元素
    public K maximum() {
        if (size == 0)
            throw new IllegalArgumentException("Empty BST!");
        Node node = root;
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    // 删除二分搜索树的最小元素
    public K removeMin() {
        K ret = minimum();
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
    public K removeMax() {
        K ret = maximum();
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
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        return node;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 删除特定的节点
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
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
        res.append(generateDepthString(depth) + node.key + "\n");
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