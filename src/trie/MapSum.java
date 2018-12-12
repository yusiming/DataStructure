package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther yusiming
 * @Date 2018/12/5 14:12
 */
public class MapSum {
    private class Node {
        boolean isWord;
        Map<Character, Node> next;
        int val;

        public Node(int val) {
            this.val = val;
            this.isWord = false;
            next = new HashMap<>();
        }

        public Node(int val, boolean isWord) {
            this.isWord = isWord;
            this.val = val;
            next = new HashMap<>();
        }

        public Node() {
            this.isWord = false;
            this.val = 0;
            next = new HashMap<>();
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * 字典树中单词的数量
     */
    private int size;

    public int getSize() {
        return size;
    }

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        root = new Node();
        size = 0;
    }

    public void insert(String key, int val) {
        if (key == null || key.length() == 0 || key.trim().equals("")) {
            throw new IllegalArgumentException("参数不合法");
        }
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            // 如果该字符不存在，创建一个新的节点
            if (!current.next.containsKey(c)) {
                current.next.put(c, new Node());
            }
            // 继续深入到下一个节点
            current = current.next.get(c);
        }
        // 如果该字符之前不是一个单词的结尾，才能够size++，否则会有逻辑上的错误
        if (!current.isWord) {
            current.isWord = true;
            size++;
        }
        current.val = val;
    }

    public int sum(String prefix) {
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.next.containsKey(c)) {
                return 0;
            }
            current = current.next.get(c);
        }

        return sum(current);
    }

    private int sum(Node node) {
        if (node.next.size() == 0) {
            return node.val;
        }
        int sum = node.val;
        for (char c : node.next.keySet()) {
            sum += sum(node.next.get(c));
        }
        return sum;
    }
}
