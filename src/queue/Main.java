package queue;

import java.util.Random;

/**
 * @Auther yusiming
 * @Date 2018/11/17 13:32
 */
public class Main {
    private static double testQueue(Queue<Integer> queue, int count) {
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int count = 100000;
        Queue<Integer> queue = new ArrayQueue<>();
        double time1 = testQueue(queue, count);

        queue = new LoopQueue<>();
        double time2 = testQueue(queue, count);
        System.out.println("ArrayQueue,time: " + time1);
        System.out.println("LoopQueue,time: " + time2);
    }
}
