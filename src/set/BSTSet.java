package set;

import tree.BST;

/**
 * 使用BST实现集合Set
 *
 * @Auther yusiming
 * @Date 2018/11/29 13:27
 */
public class BSTSet<T extends Comparable<T>> implements Set<T> {
    private BST<T> bst;

    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public void add(T t) {
        bst.add(t);
    }

    @Override
    public void remove(T t) {
        bst.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return bst.contains(t);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println('b'-'a');
    }
}
