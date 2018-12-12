package linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表
 *
 * @Auther yusiming
 * @Date 2018/11/27 22:25
 */
public class DoubleLinkedList<T> implements Iterable<T> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        T t;
        Node before;
        Node after;

        public Node(T t, Node before, Node after) {
            this.t = t;
            this.before = before;
            this.after = after;
        }

        public Node() {
            this(null, null, null);
        }

        public Node(T t) {
            this(t, null, null);
        }
    }

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 当要删除的位置为1时，其实就是从表头删除结点
        if (index == 0) {
            addAtFirst(t);
            return;
        }
        // 当要删除的位置为N时，其实就是从表尾删除结点
        if (index == size) {
            addAtLast(t);
            return;
        }
        Node current = null;
        // 遍历链表，找到要删除的结点
        if (index > size / 2) {
            current = tail;
            for (int i = 0; i < size - 1 - index; i++) {
                current = current.before;
            }
        } else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.after;
            }
        }
        Node node = new Node(t, current.before, current);
        current.before.after = node;
        current.before = node;
        size++;
    }

    /**
     * 在头部添加元素
     *
     * @param t 被添加的元素的值
     */
    public void addAtFirst(T t) {
        Node oldHead = head;
        head = new Node(t);
        head.before = null;
        if (!isEmpty()) {
            head.after = oldHead;
            oldHead.before = head;
        } else {
            head.after = null;
            tail = head;
        }
        size++;
    }

    /**
     * 在尾部添加元素
     *
     * @param t 被添加的元素
     */
    public void addAtLast(T t) {
        Node oldTail = tail;
        tail = new Node(t);
        tail.after = null;
        if (!isEmpty()) {
            tail.before = oldTail;
            oldTail.after = tail;
        } else {
            tail.before = null;
            head = tail;
        }
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 当要删除的位置为1时，其实就是从表头删除结点
        if (index == 0) {
            // 这里要直接返回
            return removeFirst();
        }
        // 当要删除的位置为N时，其实就是从表尾删除结点
        if (index == size - 1) {
            return removeLast();
        }
        Node current = null;
        // 遍历链表，找到要删除的结点
        if (index > size / 2) {
            current = tail;
            for (int i = 0; i < size - 1 - index; i++) {
                current = current.before;
            }
        } else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.after;
            }
        }
        current.before.after = current.after;
        current.after.before = current.before;
        size--;
        return current.t;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("链表为空，不能删除元素");
        }
        T t = head.t;
        head = head.after;
        size--;
        if (!isEmpty()) {
            head.before = null;
        } else {
            tail = null;
        }
        return t;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("链表为空，不能删除元素");
        }
        T t = tail.t;
        tail = tail.before;
        size--;
        if (!isEmpty()) {
            tail.after = null;
        } else {
            head = null;
        }
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (isEmpty()) {
                    throw new NoSuchElementException();
                }
                T t = current.t;
                current = current.after;
                return t;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T t : this) {
            stringBuilder.append(t);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        /*
         * 链表的更新过程：
         * A
         * B A
         * E B A
         * E C B A
         * E C B T A
         * E C B T A G
         * C B T A G
         * C B T A
         * C B T
         * C T
         * C
         */
        list.addAtFirst("A");
        list.addAtFirst("B");
        System.out.println(list);
        list.add(0, "E");
        list.add(1, "C");
        list.add(3, "T");
        list.add(5, "G");
        System.out.println(list);
        list.removeFirst();
        list.removeLast();
        System.out.println(list);
        list.remove(3);
        list.remove(1);
        list.remove(1);
        list.remove(0);
        System.out.println(list);
    }
}
