package com.funian.algorithm.algorithm;

/**
 * 有效的括号（LeetCode 20）
 *
 * 时间复杂度：O(n)
 * - n是字符串长度
 * - 需要遍历字符串一次
 *
 * 空间复杂度：O(n)
 * - 最坏情况下栈中存储所有左括号
 */
import java.util.Scanner;
import java.util.Stack;

public class IsValid20 {

    /**
     * 主函数：处理用户输入并判断括号字符串是否有效
     *
     * 算法流程：
     * 1. 读取用户输入的字符串
     * 2. 调用isValid方法判断字符串是否有效
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入字符串
        System.out.print("请输入字符串：");
        // 读取用户输入的字符串
        String s = scanner.nextLine();

        // 调用 isValid 方法判断字符串是否合法
        // 调用isValid方法判断字符串是否有效
        boolean result = isValid(s);

        // 输出结果
        // 打印判断结果
        System.out.println("字符串是否有效：" + result);

        // 关闭 Scanner
        // 关闭Scanner资源
        scanner.close();
    }

    /**
     * 判断括号字符串是否有效
     *
     * 算法思路：
     * 使用栈数据结构，遵循"后进先出"的原则
     * 1. 遇到左括号时，将其压入栈中
     * 2. 遇到右括号时，检查栈顶是否为对应的左括号
     * 3. 如果匹配则弹出栈顶元素，否则返回false
     * 4. 遍历结束后，如果栈为空则说明所有括号都匹配
     *
     * @param s 输入的括号字符串
     * @return 如果字符串有效返回true，否则返回false
     */
    public static boolean isValid(String s) {
        // 创建栈用于存放左括号
        // 栈的特点：后进先出(LIFO)
        // 创建字符栈用于存储左括号
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每一个字符
        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 如果是左括号，则压入栈中
            // 左括号需要等待对应的右括号来匹配
            // 判断是否为左括号
            if (c == '(' || c == '[' || c == '{') {
                // 将左括号压入栈中
                stack.push(c);
            }
            // 如果是右括号
            // 需要检查是否有对应的左括号匹配
            // 判断是否为右括号
            else if (c == ')' || c == ']' || c == '}') {
                // 如果栈为空，说明没有对应的左括号
                // 这是一个重要的边界条件检查
                // 检查栈是否为空
                if (stack.isEmpty()) {
                    // 栈为空说明没有匹配的左括号，返回false
                    return false;
                }

                // 弹出栈顶的左括号
                // 这里体现了栈的LIFO特性
                // 弹出栈顶元素
                char top = stack.pop();

                // 检查是否匹配
                // 每种右括号必须与对应的左括号匹配
                // 检查括号是否匹配
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    // 括号不匹配，返回false
                    return false;
                }
            }
        }

        // 如果栈为空，则所有括号均已匹配，返回 true
        // 否则说明还有未匹配的左括号，返回 false
        // 这是另一个重要的边界条件检查
        // 检查栈是否为空，空则说明所有括号都匹配
        return stack.isEmpty();
    }

    /**
     * 方法2：使用HashMap优化版本
     *
     * 算法思路：
     * 使用HashMap存储括号的对应关系，使代码更清晰
     *
     * @param s 输入的括号字符串
     * @return 如果字符串有效返回true，否则返回false
     */
    public boolean isValidHashMap(String s) {
        // 检查字符串是否为空或长度为奇数
        if (s == null || s.length() % 2 != 0) return false;

        // 创建HashMap存储括号对应关系
        java.util.Map<Character, Character> map = new java.util.HashMap<>();
        // 存储右括号')'对应的左括号'('
        map.put(')', '(');
        // 存储右括号']'对应的左括号'['
        map.put(']', '[');
        // 存储右括号'}'对应的左括号'{'
        map.put('}', '{');

        // 创建字符栈
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 如果是右括号
            // 检查字符是否为右括号
            if (map.containsKey(c)) {
                // 检查栈顶元素是否匹配
                // 获取栈顶元素，栈空时使用'#'占位符
                char top = stack.isEmpty() ? '#' : stack.pop();
                // 检查栈顶元素是否与右括号对应的左括号匹配
                if (top != map.get(c)) {
                    // 不匹配则返回false
                    return false;
                }
            } else {
                // 如果是左括号，压入栈中
                // 将左括号压入栈中
                stack.push(c);
            }
        }

        // 检查栈是否为空
        return stack.isEmpty();
    }

    /**
     * 方法3：数组模拟栈（空间优化）
     *
     * 算法思路：
     * 使用数组代替栈，避免Java集合类的开销
     *
     * @param s 输入的括号字符串
     * @return 如果字符串有效返回true，否则返回false
     */
    public boolean isValidArray(String s) {
        // 检查字符串是否为空
        if (s == null || s.length() == 0) return true;
        // 检查字符串长度是否为奇数
        if (s.length() % 2 != 0) return false;

        // 创建字符数组模拟栈
        char[] stack = new char[s.length()];
        // 初始化栈顶指针为-1（空栈状态）
        int top = -1;

        // 遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            // 判断是否为左括号
            if (c == '(' || c == '[' || c == '{') {
                // 将左括号压入栈中并更新栈顶指针
                stack[++top] = c;
            } else {
                // 检查栈是否为空
                if (top == -1) return false;

                // 弹出栈顶元素并更新栈顶指针
                char topChar = stack[top--];
                // 检查括号是否匹配
                if ((c == ')' && topChar != '(') ||
                    (c == ']' && topChar != '[') ||
                    (c == '}' && topChar != '{')) {
                    // 括号不匹配，返回false
                    return false;
                }
            }
        }

        // 检查栈是否为空
        return top == -1;
    }
}
