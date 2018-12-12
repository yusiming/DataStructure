package array;

/**
 * 对数组的封装
 *
 * @Auther yusiming
 * @Date 2018/11/14 11:22
 */
public class Array<T> {
    /**
     * 默认数组容量大小
     */
    private static final int DEFAULT_CAPACITY = 8;
    /**
     * 用来存放元素的数组
     */
    private T[] data;
    /**
     * 数组的大小
     */
    private int size;

    /**
     * 使用指定的容量初始化一个数组
     *
     * @param capacity 容量
     */
    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("参数不合法，无法创建大小为" + capacity + "的数组");
        }
        data = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * 使用默认的容量初始化一个数组
     */
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取数组的容量
     *
     * @return 数组的容量
     */
    public int getCapacity() {
        return data.length;
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
     * 判断数组是否为空
     *
     * @return 若为空，返回true，否则返回false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 判断数组是否已满
     *
     * @return 若已满，返回true，否则返回false
     */
    public boolean isFull() {
        return data.length == size;
    }

    /**
     * 在数组开头插入元素
     *
     * @param t 被插入的元素
     */
    public void addAtFirst(T t) {
        add(0, t);
    }

    /**
     * 在数组末尾添加元素
     *
     * @param t 被添加的元素
     */
    public void addAtLast(T t) {
        add(size, t);
    }

    /**
     * 在指定位置添加元素
     *
     * @param index 被添加的元素的位置的索引
     * @param t     被添加的元素
     */
    public void add(int index, T t) {
        // 如果数组已满，扩容
        if (isFull()) {
            // 使用移位操作，提高效率
            resize(data.length << 1);
        }
        // 如果要插入的元素的位置不合法抛出异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("插入元素的索引不合法:" + index);
        }
        // 将数组从index开始以及之后的元素全部向后挪动一个位置
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        // 将e添加到数组的index位置
        data[index] = t;
        // 数组中的元素的个数加一
        size++;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    /**
     * 获取指定位置元素的值
     *
     * @param index 要获取的元素在数组中的位置
     * @return 元素的值
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("元素的索引不合法:" + index);
        }
        return data[index];
    }

    /**
     * 获取数组尾部的数据
     *
     * @return 数组尾部元素
     */
    public T getLast() {
        return get(size - 1);
    }

    /**
     * 获取数组头部的数据
     *
     * @return 数组头部元素
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * 更新指定位置的元素的值
     *
     * @param index 数组的索引
     * @param t     新的值
     */
    public void set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("插入元素的索引不合法:" + index);
        }
        data[index] = t;
    }

    /**
     * 判断指定的元素是否在该集合中
     *
     * @param t 需要判断的元素
     * @return 如果包含，返回true，否则返回false
     */
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素在数组中的索引，如果元素不包含在数组中，返回-1，否则返回对应的索引
     *
     * @param t 元素e
     * @return 如果该元素在数组中，返回对应的数组下标，否则返回-1
     */
    public int findIndex(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定位置的元素,并返回删除的元素
     *
     * @param index 要删除的元素的位置
     */
    public T delete(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("要删除的元素的索引不合法:" + index);
        }
        T t = data[index];
        // 将从index+1到size-1位置的元素全部向左移动一个位置
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        // 当size=0，而data.length=1时，data.length / 4 = 0 ，所以也会进行resize，但是data.length / 2 = 0，
        // 我们不应该创建一个长度为0的数组
        if (size <= data.length / 4 && data.length / 2 != 0) {
            resize(data.length >> 1);
        }
        return t;
    }

    /**
     * 删除第一个元素
     *
     * @return 返回删除的元素的值
     */
    public T deleteFirst() {
        return delete(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return 返回删除的元素的值
     */
    public T deleteLast() {
        return delete(size - 1);
    }

    /**
     * 如果数组中存在指定的元素，就删除它，否则什么也不干
     *
     * @param t 要被删除的元素
     */
    public void deleteElement(T t) {
        int index = findIndex(t);
        // 如果元素在数组中的下标不为-1，删除之
        if (index != -1) {
            delete(index);
        }
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("参数不合法");
        }
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 字符串描述
     *
     * @return Array的字符串描述
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("数组中元素的个数:%d,数组的容量:%d\n", size, data.length));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

