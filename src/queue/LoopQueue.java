package queue;

/**
 * 循环队列
 *
 * @Auther yusiming
 * @Date 2018/11/16 19:59
 */
public class LoopQueue<T> implements Queue<T> {
    private T[] data;
    private int front;
    private int tail;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        /*
         * 因为我们要浪费一个数组空间，
         * 为了能够达到capacity长度的队列这里要将数组的长度加一
         */
        data = (T[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public LoopQueue() {
        this(DEFAULT_CAPACITY);
    }

    public int getCapacity() {
        // 我们有意浪费了一个数组空间
        return data.length - 1;
    }

    @Override
    public void enqueue(T t) {
        if (isFull()) {
            resize((data.length - 1) << 1);
        }
        data[tail] = t;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空，无法删除元素");
        }
        T t = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size <= (data.length - 1) / 4 && (data.length - 1) / 2 != 0) {
            resize((data.length - 1) / 2);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newArray[i] = data[(i + front) % data.length];
        }
        data = newArray;
        front = 0;
        tail = size;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return (tail + 1) % data.length == front;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("循环队列中元素的个数:%d,容量:%d\n", size, data.length - 1));
        stringBuilder.append("front [");
        /*for (int i = front; i != tail; i = (i + 1) % data.length) {
            stringBuilder.append(data[front]);
            if ((i + 1) % data.length != tail) {
                stringBuilder.append(",");
            }
        }*/

        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[(i + front) % data.length]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

