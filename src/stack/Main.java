package stack;

import java.util.Random;

/**
 * @Auther yusiming
 * @Date 2018/11/17 15:42
 */
public class Main {
    private static double testStack(Stack<Integer> stack, int count) {
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int count = 100000;
        Stack<Integer> stack = new ArrayStack<>();
        double time1 = testStack(stack, count);

        stack = new LinkedListStack<>();
        double time2 = testStack(stack, count);
        System.out.println("ArrayStack,time: " + time1);
        System.out.println("LinkedListStack,time: " + time2);
    }
}
