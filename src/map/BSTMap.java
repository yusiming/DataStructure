package map;

/**
 * @Auther yusiming
 * @Date 2018/11/30 10:28
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node() {
            this(null, null, null, null);
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(key, root);
            return node.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        return this.getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = this.getNode(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node != null) {
            node.value = value;
            return;
        }
        throw new IllegalArgumentException("key对应的node不存在");
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return getNode(node.left, key);
        } else if (compare > 0) {
            return getNode(node.right, key);
        }
        return node;
    }

    private Node add(K key, V value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = add(key, value, node.left);
        } else if (compare > 0) {
            node.right = add(key, value, node.right);
        } else {
            node.value = value;
        }
        return node;
    }

    private Node remove(K key, Node node) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(key, node.left);
            return node;
        } else if (compare > 0) {
            node.right = remove(key, node.right);
            return node;
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node min = min(node.right);
            min.right = removeMin(node.right);
            min.left = node.left;
            node.left = node.right = null;
            return min;
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }
}
