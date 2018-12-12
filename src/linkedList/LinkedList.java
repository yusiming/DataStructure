package linkedList;

/**
 * @Auther yusiming
 * @Date 2018/11/17 14:02
 */
public class LinkedList<T> {
    /**
     * 虚拟头节点，方便在头节点位置插入、删除节点
     */
    private Node dummyHead;
    private int size;

    /**
     * 使用内部类来屏蔽使用细节
     */
    private class Node {
        T t;
        Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node() {
            this(null, null);
        }

        public Node(T t) {
            this(t, null);
        }

        public Node(Node next) {
            this(null, next);
        }

        @Override
        public String toString() {
            return this.t.toString();
        }
    }

    public LinkedList() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 在链表头部添加元素
     *
     * @param t 要添加的元素
     */
    public void addAtFirst(T t) {
        // Node node = new Node();
        // node.t = t;
        // node.next = head;
        // head = node;
        // 上面四行等于下面一行
        // head = new Node(t, head);
        add(0, t);
    }

    /**
     * 在链表尾部添加新的元素
     *
     * @param t 被添加的元素
     */
    public void addAtLast(T t) {
        add(size, t);
    }

    /**
     * 在指定的位置添加元素
     *
     * @param index 被添加的元素的位置
     * @param t     被添加的元素
     */
    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法:" + index);
        }
        // 为了能够在头部添加元素，从dummyHead开始遍历
        Node current = dummyHead;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // 此时的current是要插入位置的前一个节点
        current.next = new Node(t, current.next);
        // Node node = new Node(t);
        // node.next = current.next;
        // current.next = node;
        size++;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index 元素的位置
     * @return 元素的值
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("参数不合法:" + index);
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.t;
    }

    /**
     * 获取链表头节点位置的元素的值
     *
     * @return 元素的值
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * 获取链表头节尾位置的元素的值
     *
     * @return 元素的值
     */
    public T getLast() {
        return get(size - 1);
    }

    /**
     * 在指定位置插入元素
     *
     * @param index index位置
     * @param t     元素的值
     */
    public void set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("参数不合法:" + index);
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.t = t;
    }

    /**
     * 查看链表中是否存在指定的元素t
     *
     * @return 如果存在指定的元素返回true，否则返回false
     */
    public boolean contains(T t) {
        for (Node current = dummyHead.next; current != null; current = current.next) {
            if (current.t == t) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除指定位置的节点
     *
     * @param index 节点位置
     */
    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("参数不合法:" + index);
        }
        // 为了能够删除首部节点的元素，从dummyHead开始遍历
        Node current = dummyHead;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Node node = current.next;
        current.next = node.next;
        node.next = null;
        size--;
        return node.t;
    }

    public T deleteAtFirst() {
        return delete(0);
    }

    public T deleteAtLast() {
        return delete(size - 1);
    }

    public void delete(T t) {
        Node current = dummyHead;
        while (current.next != null) {
            if (t.equals(current.next.t)) {
                current.next = current.next.next;
                size--;
                // 只删除一个
                break;
            }
            current = current.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node current = dummyHead.next; current != null; current = current.next) {
            stringBuilder.append(current);
            stringBuilder.append("->");
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }
}
