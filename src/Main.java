import stack.ArrayStack;

/**
 * 测试类
 *
 * @Auther yusiming
 * @Date 2018/11/14 11:07
 */
public class Main {
    public static void main(String[] args) {
        String s = "({[]})";
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
        }
    }
}

class Solution {
    public boolean isValid(String s) {
        stack.Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c || '{' == c || '[' == c) {
                stack.push(c);
            } else {
                if (c != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
