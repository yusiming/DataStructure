package segmentTree;

/**
 * @Auther yusiming
 * @Date 2018/12/4 10:43
 */
public interface Merger<T> {
    public T merge(T left, T right);
}
