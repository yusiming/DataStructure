package queue;

import array.Array;

/**
 * 复用动态数组实现队列
 *
 * @Auther yusiming
 * @Date 2018/11/16 19:28
 */
public class ArrayQueue<T> implements Queue<T> {
    private Array<T> data;

    public ArrayQueue() {
        data = new Array<>();
    }

    public ArrayQueue(int capacity) {
        data = new Array<>(capacity);
    }

    @Override
    public void enqueue(T t) {
        data.addAtLast(t);
    }

    @Override
    public T dequeue() {
        return data.deleteFirst();
    }

    /**
     * 获取队列首部的元素
     *
     * @return 队列首部元素
     */
    @Override
    public T getFront() {
        return data.getFirst();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue:");
        stringBuilder.append(" front [");
        for (int i = 0; i < data.getSize(); i++) {
            stringBuilder.append(data.get(i));
            if (i != data.getSize() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}
