package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * 分割回文串（LeetCode 131）
 *
 * 时间复杂度：O(N * 2^N)
 * - 最坏情况下有2^N个分割方案，每个方案需要O(N)时间验证和构造
 *
 * 空间复杂度：O(N)
 * - 递归调用栈深度为N
 * - 需要额外的列表存储当前路径和预处理的回文信息
 */
public class Partition131 {

    /**
     * 分割字符串使得每个子串都是回文串
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的分割方案
     * 1. 预处理：使用动态规划计算所有子串是否为回文
     * 2. 回溯：从起始位置开始，尝试每个可能的分割点
     * 3. 只有当前子串是回文时才继续递归
     * 4. 找到完整分割方案后添加到结果中
     *
     * @param s 输入字符串
     * @return 所有可能的分割方案
     */
    public List<List<String>> partition(String s) {
        // 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // 创建路径列表
        List<String> path = new ArrayList<>();

        // 预处理：计算所有子串是否为回文
        boolean[][] isPalindrome = preprocess(s);

        // 开始回溯
        backtrack(s, 0, path, isPalindrome, result);

        // 返回结果
        return result;
    }

    /**
     * 预处理：计算所有子串是否为回文
     *
     * 算法思路：
     * 使用动态规划，`dp[i][j]`表示`s.substring(i,j+1)`是否为回文
     * 状态转移方程：
     * `dp[i][j]` = `(s.charAt(i) == s.charAt(j)) && (j-i <= 2 || dp[i+1][j-1])`
     *
     * @param s 输入字符串
     * @return 二维布尔数组，`dp[i][j]`表示`s[i..j]`是否为回文
     */
    private boolean[][] preprocess(String s) {
        // 获取字符串长度
        int n = s.length();
        // 创建DP表
        boolean[][] dp = new boolean[n][n];

        // 从下到上，从左到右填充DP表
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // 检查首尾字符是否相同
                if (s.charAt(i) == s.charAt(j)) {
                    // 如果长度小于等于3，或者内部子串是回文
                    if (j - i <= 2 || dp[i + 1][j - 1]) {
                        // 标记为回文
                        dp[i][j] = true;
                    }
                }
            }
        }

        // 返回DP表
        return dp;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 递归地尝试所有可能的分割点，只在子串为回文时继续递归
     *
     * @param s 输入字符串
     * @param start 当前处理的起始位置
     * @param path 当前构建的分割方案
     * @param isPalindrome 预处理的回文信息
     * @param result 存储所有分割方案的结果列表
     */
    private void backtrack(String s, int start, List<String> path,
                          boolean[][] isPalindrome, List<List<String>> result) {
        // 递归终止条件：已处理完整个字符串
        if (start == s.length()) {
            // 将当前路径添加到结果中（需要创建新列表避免引用问题）
            result.add(new ArrayList<>(path));
            // 返回
            return;
        }

        // 尝试每个可能的分割点
        for (int i = start; i < s.length(); i++) {
            // 只有当前子串是回文时才继续
            if (isPalindrome[start][i]) {
                // 做选择：将当前子串添加到路径中
                path.add(s.substring(start, i + 1));

                // 递归：处理剩余部分
                backtrack(s, i + 1, path, isPalindrome, result);

                // 撤销选择：回溯，移除当前子串
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 方法2：不预处理，直接判断回文
     *
     * 算法思路：
     * 不预先计算回文信息，而是在回溯过程中直接判断子串是否为回文
     *
     * @param s 输入字符串
     * @return 所有可能的分割方案
     */
    public List<List<String>> partitionNoPreprocess(String s) {
        // 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // 创建路径列表
        List<String> path = new ArrayList<>();
        // 调用不预处理的回溯方法
        backtrackNoPreprocess(s, 0, path, result);
        // 返回结果
        return result;
    }

    /**
     * 不预处理的回溯辅助方法
     *
     * 算法思路：
     * 在回溯过程中直接判断子串是否为回文
     *
     * @param s 输入字符串
     * @param start 当前处理的起始位置
     * @param path 当前构建的分割方案
     * @param result 存储所有分割方案的结果列表
     */
    private void backtrackNoPreprocess(String s, int start, List<String> path,
                                      List<List<String>> result) {
        // 检查是否已处理完整个字符串
        if (start == s.length()) {
            // 添加路径副本到结果列表
            result.add(new ArrayList<>(path));
            // 返回
            return;
        }

        // 遍历所有可能的分割点
        for (int i = start; i < s.length(); i++) {
            // 直接判断子串是否为回文
            if (isPalindrome(s, start, i)) {
                // 添加子串到路径
                path.add(s.substring(start, i + 1));
                // 递归处理剩余部分
                backtrackNoPreprocess(s, i + 1, path, result);
                // 移除路径中的最后一个元素
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 判断子串是否为回文
     *
     * 算法思路：
     * 使用双指针法判断子串是否为回文
     *
     * @param s 字符串
     * @param left 左边界
     * @param right 右边界
     * @return 是否为回文
     */
    private boolean isPalindrome(String s, int left, int right) {
        // 当左指针小于右指针时循环
        while (left < right) {
            // 比较左右字符
            if (s.charAt(left) != s.charAt(right)) {
                // 返回false
                return false;
            }
            // 左指针右移
            left++;
            // 右指针左移
            right--;
        }
        // 返回true
        return true;
    }

    /**
     * 辅助方法：读取用户输入的字符串
     *
     * @return 用户输入的字符串
     */
    public static String readString() {
        // 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.print("请输入字符串: ");
        // 读取并返回字符串
        return scanner.nextLine();
    }

    /**
     * 辅助方法：打印分割方案
     *
     * @param result 分割方案列表
     */
    public static void printPartitions(List<List<String>> result) {
        // 打印标题
        System.out.println("所有分割方案：");
        // 遍历所有分割方案
        for (int i = 0; i < result.size(); i++) {
            // 打印分割方案
            System.out.println((i + 1) + ": " + result.get(i));
        }
        // 打印总计数量
        System.out.println("总共 " + result.size() + " 个方案");
    }

    /**
     * 主函数：处理用户输入并生成所有回文分割方案
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("分割回文串");

        // 读取用户输入的字符串
        String s = readString();
        // 打印输入字符串
        System.out.println("输入字符串: \"" + s + "\"");

        // 生成所有分割方案
        Partition131 solution = new Partition131();
        // 调用partition方法
        List<List<String>> result1 = solution.partition(s);
        // 调用partitionNoPreprocess方法
        List<List<String>> result2 = solution.partitionNoPreprocess(s);

        // 输出结果
        // 打印预处理方法结果标题
        System.out.println("预处理方法结果：");
        // 调用printPartitions方法打印结果
        printPartitions(result1);

        // 打印直接判断方法结果标题
        System.out.println("\n直接判断方法结果：");
        // 调用printPartitions方法打印结果
        printPartitions(result2);
    }
}
