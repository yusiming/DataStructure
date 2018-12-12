package map;

/**
 * @Auther yusiming
 * @Date 2018/11/30 09:39
 */
public interface Map<K, V> {
    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 删除key对应的value
     *
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 是否包含key对应的value
     *
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * 返回key对应的value
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 更新key对应的value值
     *
     * @param k
     * @param value
     */
    void set(K k, V value);

    /**
     * 返回map中键值对的数量
     *
     * @return
     */
    int getSize();

    /**
     * 判断map是否为空
     *
     * @return
     */
    boolean isEmpty();
}
