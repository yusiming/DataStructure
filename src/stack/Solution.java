package stack;

import java.util.Stack;

/**
 * LeetCode第20题
 * @Auther yusiming
 * @Date 2018/11/16 17:41
 */
public class Solution {
    public boolean isValid(String s) {
        // 使用栈来帮助我们实现括号匹配
        Stack<Character> stack = new Stack<>();
        // 遍历字符串中的每一个字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是左括号压入栈中
            if ('(' == c || '{' == c || '[' == c) {
                stack.push(c);
            } else {
                // 如果栈为空，证明字符串第一个字符就是一个右括号，当然不是合法的表达式
                if (stack.isEmpty()) {
                    return false;
                } else {
                    // 如果栈不为空，取出栈顶的符号
                    Character topChar = stack.pop();
                    // 判断栈顶的符号和右括号是否匹配，如果不匹配，返回false
                    if (c == ')' && topChar != '(') {
                        return false;
                    }
                    if (c == '}' && topChar != '{') {
                        return false;
                    }
                    if (c == ']' && topChar != '[') {
                        return false;
                    }
                }
            }
        }
        // 如果遍历完字符串s，栈不为空，如; (()[]{} 这种表达式，那么当然是不合法的
        return stack.isEmpty();
    }
}
