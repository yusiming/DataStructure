package set;

/**
 * @Auther yusiming
 * @Date 2018/11/29 13:26
 */
public interface Set<T> {
    void add(T t); // 不能添加重复元素到集合中

    void remove(T t);

    boolean contains(T t);

    int getSize();

    boolean isEmpty();
}
