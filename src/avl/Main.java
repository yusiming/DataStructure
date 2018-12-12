package avl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther yusiming
 * @Date 2018/12/9 16:33
 */
public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("D:\\CASES\\ DataStructure\\src\\avl\\pride-and-prejudice.txt", words)) {
            System.out.println("总单词数" + words.size());
            AVLTreeMap<String, Integer> map = new AVLTreeMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
            System.out.println("不同的单词数:" + map.getSize());
            System.out.println("单词pride出现次数::" + map.get("pride"));
            System.out.println("单词prejudice出现次数:" + map.get("prejudice"));

            System.out.println("is BST:" + map.isBST());
            System.out.println("is Balanced:" + map.isBalanced());
            long endTiem = System.nanoTime();
            System.out.println((endTiem - startTime) / 1000000000.0);
        }
    }
}