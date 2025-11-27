package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 括号生成（LeetCode 22）
 *
 * 时间复杂度：O(4^n / √n)
 * 空间复杂度：O(4^n / √n)
 */
public class Generate22 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入括号对数 n：");
        int n = scanner.nextInt();

        List<String> result = generateParenthesis(n);

        System.out.println("所有有效的括号组合：");
        for (String s : result) {
            System.out.println(s);
        }
    }

    /**
     * 生成有效括号组合的方法
     *
     * 算法思路：
     * 使用回溯算法，通过递归生成所有有效的括号组合
     * 1. 维护已使用的左括号(open)和右括号(close)数量
     * 2. 只有当左括号数量大于右括号数量时才能添加右括号
     * 3. 当当前字符串长度达到2*n时得到一个有效组合
     * 4. 剪枝：左括号不能超过n个，右括号不能超过左括号数量
     *
     * @param n 括号对数
     * @return 所有可能的有效括号组合
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * 回溯方法
     *
     * @param result 存储所有有效括号组合的结果列表
     * @param current 当前构建的括号序列
     * @param open 已使用的左括号数量
     * @param close 已使用的右括号数量
     * @param max 括号对数
     */
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        // 如果当前组合的长度达到最大长度（2*n），则添加到结果中
        if (current.length() == 2 * max) {
            result.add(current);
            return;
        }

        // 如果还可以添加左括号（左括号数量小于n）
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // 如果可以添加右括号（右括号数量小于左括号数量）
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

    /**
     * 方法2：使用StringBuilder优化字符串操作
     *
     * @param n 括号对数
     * @return 所有可能的有效括号组合
     */
    public List<String> generateParenthesisOptimized(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        backtrackOptimized(result, current, 0, 0, n);
        return result;
    }

    /**
     * 优化版回溯方法
     *
     * @param result 存储所有有效括号组合的结果列表
     * @param current 当前构建的括号序列
     * @param open 已使用的左括号数量
     * @param close 已使用的右括号数量
     * @param max 括号对数
     */
    private void backtrackOptimized(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == 2 * max) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('(');
            backtrackOptimized(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrackOptimized(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
