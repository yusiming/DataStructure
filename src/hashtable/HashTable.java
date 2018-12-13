package hashtable;

import java.util.TreeMap;

public class HashTable<K, V> {
    private TreeMap<K, V>[] hashTable;
    /**
     * 一个素数
     */
    private int M;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    /**
     * 获取key在数组中对应的索引
     *
     * @param key 被hash的key
     * @return key对应的索引
     */
    private int hash(K key) {
        // 消除负号，得到索引值
        return key.hashCode() & 0x7fffffff % M;
    }

    /**
     * 获取数组中元素的个数
     *
     * @return 数组中元素的个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 向hashTable中添加元素
     *
     * @param key   元素的键
     * @param value 元素的值
     * @throws IllegalArgumentException 参数不合法抛出此异常
     */
    public void add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("参数不合法!");
        }
        int index = hash(key);
        if (hashTable[index].containsKey(key)) {
            hashTable[index].put(key, value);
        } else {
            hashTable[index].put(key, value);
            size++;
        }
    }

    /**
     * 根据key删除对应的值
     *
     * @param key 被删除的元素的键
     * @return key对应的值
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法!");
        }
        int index = hash(key);
        V value = null;
        if (hashTable[index].containsKey(key)) {
            value = hashTable[index].remove(key);
            size--;
        }
        return value;
    }

    /**
     * 根据key设置对应的value的值
     *
     * @param key   key
     * @param value value
     * @throws IllegalArgumentException 参数不合法抛出此异常
     */
    public void set(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("参数不合法!");
        }
        int index = hash(key);
        if (!hashTable[index].containsKey(key)) {
            throw new IllegalArgumentException("键不存在!");
        }
        hashTable[index].put(key, value);
    }

    /**
     * 判断hash表中是否包含该键对应的元素
     *
     * @param key 键
     * @return 如果包含返回true，否则返回false
     */
    public boolean contains(K key) {
        int index = hash(key);
        return hashTable[index].containsKey(key);
    }

    /**
     * 根据key获取对应的value值
     *
     * @param key 键
     * @return 对应的值
     * @throws IllegalArgumentException 参数不合法抛出此异常
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("参数不合法!");
        }
        return hashTable[hash(key)].get(key);
    }
}