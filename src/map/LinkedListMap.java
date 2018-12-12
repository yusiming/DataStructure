package map;

/**
 * @Auther yusiming
 * @Date 2018/11/30 09:53
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    private Node dummyHead;
    private int size;

    private class Node {
        K key;
        V value;
        Node next;

        public Node() {
            this(null, null, null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 查询key对应的节点Node
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        Node current = dummyHead;
        while (current != null) {
            if (key.equals(current.key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = this.getNode(key);
        // map中不存在该key，则在表头添加对应的节点
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
            return;
        }
        // 更新key对应的值
        node.value = value;
        // size++;  更新值不应该size ++
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法,key");
        }
        Node current = dummyHead;
        V value = null;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                value = current.next.value;
                current.next = current.next.next;
                size--;
                break;
            }
            current = current.next;
        }
        return value;
    }

    @Override
    public boolean contains(K key) {
        return this.getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = this.getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = this.getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }
        throw new IllegalArgumentException("key对应node不存在");
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public static void main(String[] args) {

    }
}
