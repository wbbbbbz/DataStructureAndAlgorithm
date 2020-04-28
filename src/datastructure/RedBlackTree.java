package datastructure;

public class RedBlackTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = RED;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node() {
            this(null, null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

    public boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
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

    // 向红黑树中插入新的元素
    public void add(K key, V value) {
        root = add(root, key, value);
        // 保持根节点为黑色
        root.color = BLACK;
    }

    // 向以node为根的红黑树中插入(key, value)的节点
    // 返回插入新节点后的红黑树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, null, null); // 默认是红色节点
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);
        return node;
    }

    // 左旋转
    // 返回node为根的红黑树左旋转之后新的红黑树的根
    private Node leftRotate(Node node) {
        // 左旋转前,父节点为oldParent,右节点为oldRight
        Node oldParent = node;
        Node oldRight = oldParent.right;
        oldParent.right = oldRight.left;
        oldRight.left = oldParent;

        // 左旋转后的颜色交换
        oldRight.color = oldParent.color;
        oldParent.color = RED;

        return oldRight;
    }

    // 颜色反转
    // 以node为根节点的根，左节点，右节点进行颜色翻转
    // 传入的node的具体形状判断在调用的时候判断即可
    // 保证传入的时候一定是正确的形状
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;

    }

    // 右旋转
    // 返回node为根的红黑树右旋转之后新的红黑树的根
    private Node rightRotate(Node node) {
        // 右旋转前,父节点为newRight,左节点为newRoot
        Node newRight = node;
        Node newRoot = newRight.left;
        newRight.left = newRoot.right;
        newRoot.right = newRight;

        // 右旋转后的颜色交换
        newRoot.color = newRight.color;
        newRight.color = RED;

        return newRoot;
    }

    public V remove(K key) {
        Node node = getNode(key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return node;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }
        if (node.left == null) {
            Node delNode = node;
            node = node.right;
            delNode.right = null;
            size--;
            return node;
        }
        if (node.right == null) {
            Node delNode = node;
            node = node.left;
            delNode.left = null;
            size--;
            return node;
        }
        Node successor = getMinimumNode(node.right);
        Node delNode = node;
        successor.right = removeMin(node.right);
        successor.left = node.left;
        delNode.left = null;
        delNode.right = null;
        return successor;

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