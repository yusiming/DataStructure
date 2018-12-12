package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther yusiming
 * @Date 2018/12/5 11:26
 */

public class WordDictionary {
    private Node root;
    private int size;

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
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node();
        size = 0;
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
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
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
     * letter.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0 || word.trim().equals("")) {
            throw new IllegalArgumentException("参数不合法");
        }
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (word.length() == index) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (!node.next.containsKey(c)) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            for (Character character : node.next.keySet()) {
                if (match(node.next.get(character),word,index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}

