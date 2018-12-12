package stack;

import array.Array;

/**
 * 复用动态数组实现stack
 *
 * @Auther yusiming
 * @Date 2018/11/16 16:26
 */
public class ArrayStack<T> implements Stack<T> {
    private Array<T> data;

    public ArrayStack() {
        data = new Array<>();
    }

    public ArrayStack(int capacity) {
        data = new Array<>(capacity);
    }

    @Override
    public void push(T t) {
        data.addAtLast(t);
    }

    @Override
    public T pop() {
        return data.deleteLast();
    }

    @Override
    public T peek() {
        return data.getLast();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack");
        stringBuilder.append("[");
        for (int i = 0; i < data.getSize(); i++) {
            stringBuilder.append(data.get(i));
            if (i != data.getSize() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] =>top");
        return stringBuilder.toString();
    }
}
