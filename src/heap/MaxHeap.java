package heap;

import array.Array;

import java.util.NoSuchElementException;

/**
 * 最大堆
 *
 * @Auther yusiming
 * @Date 2018/12/1 13:21
 */
public class MaxHeap<T extends Comparable<T>> {
    private Array<T> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 获取指定节点的父节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("索引为0的节点没有父节点");
        }
        return index * (index - 1) / 2;
    }

    /**
     * 获取指定节点的左子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return (index * 2 + 1);
    }

    /**
     * 获取指定节点的右子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return (index * 2 + 2);
    }

    /**
     * 向堆中添加元素
     *
     * @param t
     */
    public void add(T t) {
        if (t == null) {
            return;
        }
        data.addAtLast(t);
        swim(data.getSize() - 1);
    }

    /**
     * 元素的上浮
     *
     * @param index 要上浮的元素的索引
     */
    private void swim(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    /**
     * 查看堆中最大的元素的值
     *
     * @return
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("堆为空");
        }
        return data.get(0);
    }

    /**
     * 删除并返回最大值
     *
     * @return 最大值
     */
    public T delMax() {
        T max = getMax();
        // 将最后一个节点与根节点交换位置
        data.swap(0, data.getSize() - 1);
        data.deleteLast();
        sink(0);
        return max;
    }

    private void sink(int index) {
        while (leftChild(index) < getSize()) {
            int i = leftChild(index);
            if (i + 1 < getSize() && data.get(i + 1).compareTo(data.get(i)) > 0) {
                i ++;
            }
            if (data.get(index).compareTo(data.get(i) ) >= 0) {
                break;
            }
            data.swap(index,i);
            index = i;
        }
    }

}
