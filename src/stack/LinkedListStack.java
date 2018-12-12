package stack;

import linkedList.LinkedList;

/**
 * @Auther yusiming
 * @Date 2018/11/17 15:33
 */
public class LinkedListStack<T> implements Stack<T> {
    private LinkedList<T> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(T t) {
        linkedList.addAtFirst(t);
    }

    @Override
    public T pop() {
        return linkedList.deleteAtFirst();
    }

    @Override
    public T peek() {
        return linkedList.getFirst();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
            if (i / 3 == 2) {
                stack.pop();
                System.out.println(stack);
            }
        }
    }
}
