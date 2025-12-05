package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 字符串解码（LeetCode 394）
 *
 * 时间复杂度：O(n)
 * - n是解码后字符串的长度
 * - 每个字符最多被处理常数次
 *
 * 空间复杂度：O(n)
 * - 栈空间和StringBuilder空间
 * - 最坏情况下存储解码后的整个字符串
 */
public class DecodeString394 {

    /**
     * 主函数：处理用户输入并解码字符串
     *
     * 算法流程：
     * 1. 读取用户输入的编码字符串
     * 2. 调用decodeString方法进行解码
     * 3. 输出解码结果
     */
    public static void main(String[] args) {
        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 打印输入提示
        System.out.println("请输入经过编码的字符串（如: 3[a2[bc]]）：");
        // 读取用户输入的编码字符串
        String s = scanner.nextLine();

        // 调用decodeString方法进行解码
        String decodedString = decodeString(s);
        // 打印解码结果
        System.out.println("解码后的字符串: " + decodedString);
    }

    /**
     * 解码字符串
     *
     * 算法思路：
     * 使用两个栈分别存储重复次数和字符串
     * 1. 遇到数字时，计算完整的重复次数
     * 2. 遇到'['时，将当前重复次数和字符串入栈，并重置
     * 3. 遇到']'时，弹出栈顶元素进行解码操作
     * 4. 遇到普通字符时，直接添加到当前字符串
     *
     * @param s 输入的编码字符串
     * @return 解码后的字符串
     */
    public static String decodeString(String s) {
        // 使用栈来处理字符和重复次数

        // 存储重复次数的栈
        Stack<Integer> countStack = new Stack<>();

        // 存储字符串的栈
        Stack<String> stringStack = new Stack<>();

        // 当前正在构建的字符串
        StringBuilder currentString = new StringBuilder();

        // 当前的重复次数
        int k = 0;

        // 遍历字符串中的每一个字符
        for (char ch : s.toCharArray()) {
            // 如果是数字字符
            if (Character.isDigit(ch)) {
                // 更新重复次数 k
                // 处理多位数的情况，如"123[abc]"
                k = k * 10 + (ch - '0');
            }
            // 如果遇到 '['
            else if (ch == '[') {
                // 将当前字符串和重复次数入栈
                countStack.push(k);
                stringStack.push(currentString.toString());

                // 重置 k 和 currentString
                // 开始处理新的嵌套层级
                k = 0;
                currentString = new StringBuilder();
            }
            // 如果遇到 ']'
            else if (ch == ']') {
                // 弹出栈顶的重复次数和字符串
                StringBuilder decodedString = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();

                // 重复当前字符串并拼接
                // 将currentString重复repeatTimes次并添加到decodedString后面
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }

                // 更新当前字符串为解码后的结果
                currentString = decodedString;
            }
            // 其他字符（字母）
            else {
                // 其他字符直接添加到 currentString
                currentString.append(ch);
            }
        }

        // 返回解码后的字符串
        return currentString.toString();
    }

    /**
     * 方法2：递归解法
     *
     * 算法思路：
     * 使用递归处理嵌套结构
     *
     * @param s 输入的编码字符串
     * @return 解码后的字符串
     */
    private int index = 0;

    public String decodeStringRecursive(String s) {
        // 重置索引
        index = 0;
        // 调用递归辅助方法
        return decodeStringHelper(s);
    }

    private String decodeStringHelper(String s) {
        // 创建结果构建器
        StringBuilder result = new StringBuilder();
        // 初始化重复次数
        int k = 0;

        // 当索引未超出字符串长度时循环
        while (index < s.length()) {
            // 获取当前字符
            char ch = s.charAt(index);
            // 索引递增
            index++;

            // 判断字符是否为数字
            if (Character.isDigit(ch)) {
                // 计算多位数
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // 递归处理括号内的内容
                String nested = decodeStringHelper(s);
                // 重复k次并添加到结果中
                while (k > 0) {
                    // 将嵌套内容追加到结果
                    result.append(nested);
                    // 递减重复次数
                    k--;
                }
            } else if (ch == ']') {
                // 遇到右括号，返回当前层级的结果
                break;
            } else {
                // 普通字符直接添加
                result.append(ch);
            }
        }

        // 返回解码结果
        return result.toString();
    }

    /**
     * 方法3：使用单个栈优化版本
     *
     * 算法思路：
     * 使用单个栈存储数字和字符串，交替存储
     *
     * @param s 输入的编码字符串
     * @return 解码后的字符串
     */
    public String decodeStringOptimized(String s) {
        // 创建单个栈
        Stack<String> stack = new Stack<>();
        // 创建当前字符串构建器
        StringBuilder currentString = new StringBuilder();
        // 初始化重复次数
        int k = 0;

        // 遍历字符串中的每个字符
        for (char ch : s.toCharArray()) {
            // 判断字符是否为数字
            if (Character.isDigit(ch)) {
                // 计算多位数
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // 将数字和字符串都压入同一个栈
                stack.push(String.valueOf(k));
                stack.push(currentString.toString());
                // 重置重复次数
                k = 0;
                // 重置当前字符串
                currentString = new StringBuilder();
            } else if (ch == ']') {
                // 弹出字符串和数字
                String prevString = stack.pop();
                int repeatTimes = Integer.parseInt(stack.pop());

                // 构造重复字符串
                StringBuilder temp = new StringBuilder(prevString);
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentString);
                }
                // 更新当前字符串
                currentString = temp;
            } else {
                // 将字符追加到当前字符串
                currentString.append(ch);
            }
        }

        // 返回解码后的字符串
        return currentString.toString();
    }
}
