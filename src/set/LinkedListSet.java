package set;

import linkedList.LinkedList;

/**
 * 使用链表实现Set集合
 *
 * @Auther yusiming
 * @Date 2018/11/29 13:45
 */
public class LinkedListSet<T extends Comparable> implements Set<T> {
    private LinkedList<T> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(T t) {
        if (!linkedList.contains(t)) {
            linkedList.addAtFirst(t);
        }
    }

    @Override
    public void remove(T t) {
        linkedList.delete(t);
    }

    @Override
    public boolean contains(T t) {
        return linkedList.contains(t);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
