package datastructure;

import java.util.TreeMap;

public class TrieWithSum {

    private class Node {
        public boolean isWord;
        public int val;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
            val = 0;
        }

        public Node() {
            this(false);
        }

        @Override
        public String toString() {
            return "IsWord: " + isWord + ", next: " + next + ", val: " + val;
        }
    }

    private Node root;

    public TrieWithSum() {
        root = new Node();
    }

    // 向Trie中添加一个新的单词word
    public void add(String word, int val) {

        Node cur = root;
        // 每一位对应一个节点。该信息存储在当前节点的next中；
        // 如果结束，记录上isWord
        if (!word.isBlank()) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                // 查询下一节点是否存在
                // 不存在就新建节点，记录在next中
                if (!cur.next.containsKey(c))
                    cur.next.put(c, new Node());

                // 通过next映射进入下一节点
                cur = cur.next.get(c);
            }
        }
        // 到单词末尾时
        // 有一个小问题，因为如果已经记录了是一个单词，那么size就不能加1！
        cur.isWord = true;
        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        // 遍历prefix，找到最后一个node
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (!cur.next.containsKey(c))
                return 0;
            cur = cur.next.get(c);
        }
        return getSum(cur);
    }

    private int getSum(Node cur) {
        int res = cur.val;

        for (Node node : cur.next.values()) {
            res += getSum(node);
        }

        return res;
    }

    // 查询单词word是否再Trie中
    // 整体逻辑与add类似
    public boolean containsNR(String word) {
        Node cur = root;
        // 每一位对应一个节点。该信息存储在当前节点的next中；
        // 如果没有对应信息，则返回false
        if (!word.isBlank()) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                // 查询下一节点是否存在
                // 不存在就返回false
                if (!cur.next.containsKey(c))
                    return false;

                // 通过next映射进入下一节点
                cur = cur.next.get(c);
            }
        }
        // 到单词末尾时直接返回cur.isWord即可
        // 因为字典中不一定有该单词（比如只进来了部分的单词！）
        return cur.isWord;
    }

    // 查找的递归写法
    // 对应正则式中的"."
    public boolean contains(String word) {
        return contains(root, word, 0);
    }

    private boolean contains(Node node, String word, int wordIndex) {
        // 1.递归的基本条件
        // 可以直接到length(),因为trie多一个节点！
        if (wordIndex == word.length()) {
            return node.isWord;
        }

        Character c = word.charAt(wordIndex);

        // 2.递归的递推条件
        // 如果是"."返回所有next节点的布尔值的和
        // 否则返回对应节点的值
        boolean res = false;

        if (c != '.')
            return node.next.containsKey(c) && contains(node.next.get(c), word, wordIndex + 1);
        for (Node nextNode : node.next.values()) {
            res = res || contains(nextNode, word, wordIndex + 1);
        }
        return res;
    }

    // 查询prefix是否是某个单词的前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        if (prefix.isBlank())
            return false;
        // 每一位对应一个节点。该信息存储在当前节点的next中；
        // 如果没有对应信息，则返回false
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            // 查询下一节点是否存在
            // 不存在就返回false
            if (!cur.next.containsKey(c))
                return false;

            // 通过next映射进入下一节点
            cur = cur.next.get(c);
        }

        // 此时还不一定能判断是否是一个单词的前缀，需要遍历到叶子节点，看是否有节点的isWord为true的
        // return hasWord(cur);
        return true;
    }

    // 判断从node开始的子树内部有没有单词存在
    private boolean hasWord(Node node) {
        // 1.递归的基本条件
        if (node.isWord == true)
            return true;

        // 2.递归的递推条件
        // 返回所有next节点的布尔值的和
        boolean res = false;
        for (Node nextNode : node.next.values()) {
            res = res || hasWord(nextNode);
        }
        return res;

    }
}