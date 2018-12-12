package queue;

/**
 * 使用链表实现队列
 *
 * @Auther yusiming
 * @Date 2018/11/26 13:08
 */
public class LinkedListQueue<T> implements Queue<T> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        T t;
        Node next;

        public Node() {
            this.t = null;
            this.next = null;
        }

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this.t = t;
            this.next = null;
        }

        public Node(Node next) {
            this.t = null;
            this.next = next;
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }

    public LinkedListQueue() {
        tail = null;
        head = null;
        size = 0;
    }

    /**
     * 从队列尾部添加元素
     *
     * @param t 要添加的元素
     */
    @Override
    public void enqueue(T t) {
        if (isEmpty()) {
            tail = new Node(t);
            head = tail;
        } else {
            tail.next = new Node(t);
            tail = tail.next;
        }
        size++;
    }

    /**
     * 从队列头部删除元素
     *
     * @return 被删除的元素
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        T t = head.t;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return t;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return head.t;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue:front ");
        for (Node current = head; current != null; current = current.next) {
            stringBuilder.append(current.t).append("->");
        }
        stringBuilder.append("NULL tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue);
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
    }
}
