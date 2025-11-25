package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 最长有效括号（LeetCode 32）- 动态规划/栈
 *
 * 时间复杂度：
 * - 方法1（动态规划）：O(n)
 *   只需要遍历字符串一次
 * - 方法2（栈）：O(n)
 *   只需要遍历字符串一次
 *
 * 空间复杂度：
 * - 方法1（动态规划）：O(n)
 *   需要长度为n的DP数组
 * - 方法2（栈）：O(n)
 *   栈最多存储n个元素
 */
public class LongestValidParentheses32 {

    /**
     * 计算最长有效括号子串的长度
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示以`s[i]`结尾的最长有效括号子串的长度
     *
     * 状态转移：
     * 1. 如果`s[i]` = '('，则`dp[i]` = 0（以'('结尾不可能是有效括号）
     * 2. 如果`s[i]` = ')'：
     *    a. 如果`s[i-1]` = '('，则`dp[i]` = `dp[i-2]` + 2
     *    b. 如果`s[i-1]` = ')'且`dp[i-1]` > 0：
     *       - 如果`s[i-dp[i-1]-1]` = '('，则`dp[i]` = `dp[i-1]` + 2 + `dp[i-dp[i-1]-2]`
     *
     * 执行过程分析（以`s=")()())"`为例）：
     *
     * 初始化DP数组：
     * dp = [0, 0, 0, 0, 0, 0]
     * 索引: 0  1  2  3  4  5
     * 字符: )  (  )  (  )  )
     *
     * 填充DP数组：
     * i=0, `s[0]=')'`: `dp[0]` = 0
     *
     * i=1, `s[1]='('`: `dp[1]` = 0
     *
     * i=2, `s[2]=')'`, `s[1]='('`:
     *   `dp[2]` = `dp[0]` + 2 = 0 + 2 = 2
     *   dp = [0, 0, 2, 0, 0, 0]
     *
     * i=3, `s[3]='('`: `dp[3]` = 0
     *
     * i=4, `s[4]=')'`, `s[3]='('`:
     *   `dp[4]` = `dp[2]` + 2 = 2 + 2 = 4
     *   dp = [0, 0, 2, 0, 4, 0]
     *
     * i=5, `s[5]=')'`, `s[4]=')'`, `dp[4]=4`:
     *   检查`s[5-dp[4]-1]` = `s[5-4-1]` = `s[0]` = ')'
     *   不匹配，`dp[5]` = 0
     *   dp = [0, 0, 2, 0, 4, 0]
     *
     * 最终结果：max(dp) = 4
     * 最长有效括号子串："()()"，长度为4
     *
     * 时间复杂度分析：
     * - 遍历字符串一次：O(n)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     *
     * @param s 输入的括号字符串
     * @return 最长有效括号子串的长度
     */
    public int longestValidParenthesesDP(String s) {
        if (s == null || s.length() <= 1) return 0;

        // dp[i] 表示以 s[i] 结尾的最长有效括号子串的长度
        int[] dp = new int[s.length()];
        int maxLength = 0;

        // 从第二个字符开始遍历
        for (int i = 1; i < s.length(); i++) {
            // 只有当当前字符是 ')' 时才可能形成有效括号
            if (s.charAt(i) == ')') {
                // 情况1：s[i-1] = '('，即 "...()"
                if (s.charAt(i - 1) == '(') {
                    // 长度为前前位置的最长长度 + 2
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // 情况2：s[i-1] = ')' 且 dp[i-1] > 0，即 "...))" 形式
                else if (dp[i - 1] > 0) {
                    // 计算匹配位置
                    int matchIndex = i - dp[i - 1] - 1;
                    // 如果匹配位置有效且为 '('
                    if (matchIndex >= 0 && s.charAt(matchIndex) == '(') {
                        // 长度为中间部分长度 + 2 + 匹配位置前的最长长度
                        dp[i] = dp[i - 1] + 2 + (matchIndex > 0 ? dp[matchIndex - 1] : 0);
                    }
                }
            }

            // 更新最大长度
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    /**
     * 方法2：使用栈解法
     *
     * 算法思路：
     * 使用栈存储字符下标，维护有效括号的边界
     * 1. 栈底始终保存最后一个未匹配的')'的下标或-1
     * 2. 遇到'('时将其下标入栈
     * 3. 遇到')'时弹出栈顶元素：
     *    - 如果栈为空，说明当前')'无法匹配，将其下标入栈作为新的边界
     *    - 如果栈不为空，当前有效长度为 i - stack.peek()
     *
     * 执行过程分析（以`s=")()())"`为例）：
     *
     * 初始状态：
     * stack = [-1]
     * maxLength = 0
     *
     * 遍历过程：
     * i=0, `s[0]=')'`:
     *   弹出-1，栈为空
     *   将0入栈，stack = [0]
     *
     * i=1, `s[1]='('`:
     *   将1入栈，stack = [0, 1]
     *
     * i=2, `s[2]=')'`:
     *   弹出1，栈不为空
     *   当前长度 = 2 - 0 = 2
     *   maxLength = max(0, 2) = 2
     *
     * i=3, `s[3]='('`:
     *   将3入栈，stack = [0, 3]
     *
     * i=4, `s[4]=')'`:
     *   弹出3，栈不为空
     *   当前长度 = 4 - 0 = 4
     *   maxLength = max(2, 4) = 4
     *
     * i=5, `s[5]=')'`:
     *   弹出0，栈为空
     *   将5入栈，stack = [5]
     *
     * 最终结果：maxLength = 4
     *
     * 时间复杂度分析：
     * - 遍历字符串一次：O(n)
     *
     * 空间复杂度分析：
     * - 栈存储空间：O(n)
     *
     * @param s 输入的括号字符串
     * @return 最长有效括号子串的长度
     */
    public int longestValidParenthesesStack(String s) {
        if (s == null || s.length() <= 1) return 0;

        Stack<Integer> stack = new Stack<>();
        // 栈底初始化为-1，作为边界
        stack.push(-1);
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 遇到'('，将其下标入栈
                stack.push(i);
            } else {
                // 遇到')'，弹出栈顶元素
                stack.pop();

                if (stack.isEmpty()) {
                    // 如果栈为空，说明当前')'无法匹配
                    // 将其下标入栈作为新的边界
                    stack.push(i);
                } else {
                    // 如果栈不为空，计算当前有效长度
                    int currentLength = i - stack.peek();
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        return maxLength;
    }

    /**
     * 方法3：双向扫描解法
     *
     * 算法思路：
     * 从左到右扫描一次，从右到左扫描一次
     * 分别统计左右括号的数量，当数量相等时更新最大长度
     *
     * 时间复杂度分析：
     * - 双向扫描：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param s 输入的括号字符串
     * @return 最长有效括号子串的长度
     */
    public int longestValidParenthesesScan(String s) {
        if (s == null || s.length() <= 1) return 0;

        int left = 0, right = 0;
        int maxLength = 0;

        // 从左到右扫描
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;

        // 从右到左扫描
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return maxLength;
    }

    /**
     * 辅助方法：读取用户输入的字符串
     *
     * 时间复杂度分析：
     * - 读取输入：O(n)
     *
     * 空间复杂度分析：
     * - 存储字符串：O(n)
     *
     * @return 用户输入的字符串
     */
    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入括号字符串：");
        return scanner.nextLine();
    }

    /**
     * 主函数：处理用户输入并计算最长有效括号子串的长度
     */
    public static void main(String[] args) {
        System.out.println("最长有效括号");

        // 读取用户输入的字符串
        String s = readString();
        System.out.println("输入字符串: \"" + s + "\"");

        // 计算最长有效括号子串的长度
        LongestValidParentheses32 solution = new LongestValidParentheses32();
        int result1 = solution.longestValidParenthesesDP(s);
        int result2 = solution.longestValidParenthesesStack(s);
        int result3 = solution.longestValidParenthesesScan(s);

        // 输出结果
        System.out.println("动态规划方法结果: " + result1);
        System.out.println("栈方法结果: " + result2);
        System.out.println("双向扫描方法结果: " + result3);
    }
}
