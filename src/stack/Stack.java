package stack;

/**
 * Stack接口
 *
 * @Auther yusiming
 * @Date 2018/11/16 16:25
 */
public interface Stack<T> {
    void push(T t);

    T pop();

    T peek();

    int getSize();

    boolean isEmpty();
}
