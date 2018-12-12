package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 *
 * @Auther yusiming
 * @Date 2018/12/5 10:02
 */
public class Trie {
    /**
     * 字典树中节点的表示
     */
    private class Node {
        boolean isWord;
        Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this.isWord = false;
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

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加一个字符串
     *
     * @param word 被添加的字符串
     * @throws IllegalArgumentException 如果参数不合法，抛出此异常,不合法的字符串 null,""," "
     */
    public void add(String word) {
        if (word == null || word.length() == 0 || word.trim().equals("")) {
            throw new IllegalArgumentException("参数不合法");
        }
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
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
    }

    /**
     * 查询字典树是否包含某个单词
     *
     * @param word 被查询的单词
     * @return 如果该单词在字典树中返回true，否则返回false
     * @throws IllegalArgumentException 如果参数不合法，抛出此异常,不合法的字符串 null,""," "
     */
    public boolean contains(String word) {
        if (word == null || word.length() == 0 || word.trim().equals("")) {
            throw new IllegalArgumentException("参数不合法");
        }
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!current.next.containsKey(c)) {
                return false;
            }
            current = current.next.get(c);
        }
        return current.isWord;
    }

    /**
     * 查询是否存在以prefix为前缀的单词
     *
     * @param prefix 前缀
     * @return 如果存在返回true，否则返回false
     * @throws IllegalArgumentException 如果参数不合法，抛出此异常,不合法的字符串 null,""," "
     */
    public boolean isPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0 || prefix.trim().equals("")) {
            throw new IllegalArgumentException("参数不合法");
        }
        Node current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!current.next.containsKey(c)) {
                return false;
            }
            current = current.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.contains("cat"));
        trie.add("cat");
        System.out.println(trie.contains("cat"));
        System.out.println(trie.contains("dog"));
        trie.add("dog");
        System.out.println(trie.contains("dog"));
        System.out.println(trie.contains("panda"));
        trie.add("panda");
        System.out.println(trie.contains("panda"));
        // pan
        // trie.add("pan");
        System.out.println(trie.contains("pan"));
        System.out.println(trie.isPrefix("pan"));

    }
}
