package queue;

/**
 * @Auther yusiming
 * @Date 2018/11/16 19:26
 */
public interface Queue<T> {
    void enqueue(T t);

    T dequeue();

    T getFront();

    int getSize();

    boolean isEmpty();
}
