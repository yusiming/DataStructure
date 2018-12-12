package queue;

import heap.MaxHeap;


/**
 * 优先队列，使用最大堆可以轻松完成优先队列
 *
 * @Auther yusiming
 * @Date 2018/12/2 09:59
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    // 最大堆
    private MaxHeap<T> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeap<>(capacity);
    }

    @Override
    public void enqueue(T t) {
        maxHeap.add(t);
    }

    @Override
    public T dequeue() {
        return maxHeap.delMax();
    }

    @Override
    public T getFront() {
        return maxHeap.getMax();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }


    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}



