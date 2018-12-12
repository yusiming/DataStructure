package queue;

import java.util.*;
import java.util.PriorityQueue;

public class Solution {

    public List<Integer> topKFrequent(int[] nums, int k) {
        // 统计词频
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (int key : map.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (map.get(key) > map.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }
        List<Integer> list = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            list.add(priorityQueue.remove());
        }
        return list;
    }

    public static void main(String[] args) {
        int[] muns = {4,1,-1,2,-1,2,3};
        Solution solution = new Solution();
        List<Integer> list = solution.topKFrequent(muns, 2);
        System.out.println(list);
    }
}