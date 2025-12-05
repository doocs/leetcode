package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * 单词拆分（LeetCode 139）- 动态规划
 *
 * 时间复杂度：O(n² * m)
 * - n是字符串`s`的长度
 * - m是单词字典的大小
 * - 外层循环运行n次，内层循环运行n次，每次需要检查子串是否在字典中（最坏情况O(n)）
 *
 * 空间复杂度：O(n + m)
 * - 需要长度为n+1的DP数组和存储字典的哈希集合
 */
public class WordBreak139 {

    /**
     * 判断字符串是否能被字典中的单词拆分
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示字符串`s`的前i个字符能否被拆分
     * 状态转移方程：
     * `dp[i] = OR(dp[j] && s.substring(j, i) in wordDict)` for all j where 0 <= j < i
     *
     * 执行过程分析（以`s="leetcode"`, `wordDict=["leet","code"]`为例）：
     *
     * 初始化DP数组（n=8）：
     * dp = [T, F, F, F, F, F, F, F, F]
     * 索引: 0  1  2  3  4  5  6  7  8
     *
     * 填充DP数组：
     * i=1: 检查子串"l"，不在字典中，dp[1]=F
     * i=2: 检查子串"le","l"，都不在字典中，dp[2]=F
     * i=3: 检查子串"lee","le","l"，都不在字典中，dp[3]=F
     * i=4: 检查子串"leet"(j=0)、"eet"(j=1)、"et"(j=2)、"t"(j=3)
     *      dp[0]=T且"leet"在字典中，dp[4]=T
     * i=5: 检查子串"leetc"(j=0到4)，都不满足条件，dp[5]=F
     * i=6: 检查子串"leetco"(j=0到5)，都不满足条件，dp[6]=F
     * i=7: 检查子串"leetcod"(j=0到6)，都不满足条件，dp[7]=F
     * i=8: 检查子串"leetcode"(j=0到7)
     *      j=4时，dp[4]=T且"code"在字典中，dp[8]=T
     *
     * 最终结果：dp[8] = T，可以拆分
     *
     * 时间复杂度分析：
     * - 初始化DP数组：O(1)
     * - 填充DP数组：O(n² * m)
     * - 总时间复杂度：O(n² * m)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     * - 哈希集合存储空间：O(m)
     * - 总空间复杂度：O(n + m)
     *
     * @param s 待拆分的字符串
     * @param wordDict 单词字典
     * @return 如果能被拆分返回true，否则返回false
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 将字典转换为哈希集合，提高查找效率
        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i] 表示字符串s的前i个字符能否被拆分
        boolean[] dp = new boolean[s.length() + 1];

        // 初始化：空字符串可以被拆分
        dp[0] = true;

        // 动态规划填充DP数组
        for (int i = 1; i <= s.length(); i++) {
            // 尝试所有可能的分割点
            for (int j = 0; j < i; j++) {
                // 如果前j个字符可以拆分，且从j到i的子串在字典中，则前i个字符可以拆分
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 找到一种可行方案即可退出内层循环
                }
            }
        }

        // 返回整个字符串是否可以被拆分
        return dp[s.length()];
    }

    /**
     * 方法2：优化版本（提前终止）
     *
     * 算法思路：
     * 记录字典中最长单词的长度，避免检查过长的子串
     *
     * 时间复杂度分析：
     * - 计算最长单词长度：O(m)
     * - 填充DP数组：O(n * maxLen * m)
     * - 总时间复杂度：O(n * maxLen * m)，其中maxLen为字典中最长单词长度
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     * - 哈希集合存储空间：O(m)
     * - 总空间复杂度：O(n + m)
     *
     * @param s 待拆分的字符串
     * @param wordDict 单词字典
     * @return 如果能被拆分返回true，否则返回false
     */
    public boolean wordBreakOptimized(String s, List<String> wordDict) {
        // 将字典转换为哈希集合，提高查找效率
        Set<String> wordSet = new HashSet<>(wordDict);

        // 计算字典中最长单词的长度
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }

        // dp[i] 表示字符串s的前i个字符能否被拆分
        boolean[] dp = new boolean[s.length() + 1];

        // 初始化：空字符串可以被拆分
        dp[0] = true;

        // 动态规划填充DP数组
        for (int i = 1; i <= s.length(); i++) {
            // 从i-1开始向前检查，但不超过最长单词长度
            for (int j = i - 1; j >= 0 && i - j <= maxLength; j--) {
                // 如果前j个字符可以拆分，且从j到i的子串在字典中，则前i个字符可以拆分
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 找到一种可行方案即可退出内层循环
                }
            }
        }

        // 返回整个字符串是否可以被拆分
        return dp[s.length()];
    }

    /**
     * 方法3：记忆化递归解法
     *
     * 算法思路：
     * 使用递归方式从字符串开头开始尝试所有可能的拆分，
     * 并使用记忆化数组避免重复计算相同子问题
     *
     * 时间复杂度分析：
     * - 每个子问题只计算一次：O(n)
     * - 每个子问题需要尝试所有可能的单词：O(m * n)
     * - 总时间复杂度：O(n² * m)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(n)
     * - 记忆化数组：O(n)
     * - 哈希集合存储空间：O(m)
     * - 总空间复杂度：O(n + m)
     *
     * @param s 待拆分的字符串
     * @param wordDict 单词字典
     * @return 如果能被拆分返回true，否则返回false
     */
    public boolean wordBreakMemo(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length() + 1];
        return wordBreakHelper(s, wordSet, 0, memo);
    }

    /**
     * 记忆化递归辅助方法
     *
     * 算法思路：
     * 递归地检查从start位置开始的子串是否可以被拆分
     *
     * @param s 待拆分的字符串
     * @param wordSet 单词集合
     * @param start 当前检查的起始位置
     * @param memo 记忆化数组
     * @return 从start位置开始的子串是否可以被拆分
     */
    private boolean wordBreakHelper(String s, Set<String> wordSet, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end)) &&
                wordBreakHelper(s, wordSet, end, memo)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return false;
    }

    /**
     * 辅助方法：读取用户输入的字符串列表
     *
     * 时间复杂度分析：
     * - 读取和处理输入：O(k)，k为输入字符串数量
     *
     * 空间复杂度分析：
     * - 存储字符串列表：O(k)
     *
     * @param prompt 提示信息
     * @return 字符串列表
     */
    public static List<String> readStringList(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        List<String> list = new ArrayList<>();
        for (String str : strArray) {
            list.add(str);
        }

        return list;
    }

    /**
     * 主函数：处理用户输入并判断单词拆分
     */
    public static void main(String[] args) {
        System.out.println("单词拆分问题");

        // 读取用户输入的字符串
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入待拆分的字符串: ");
        String s = scanner.nextLine();

        // 读取单词字典
        List<String> wordDict = readStringList("请输入单词字典（用空格分隔）:");
        System.out.println("待拆分字符串: \"" + s + "\"");
        System.out.println("单词字典: " + wordDict);

        // 判断是否可以拆分
        WordBreak139 solution = new WordBreak139();
        boolean result1 = solution.wordBreak(s, wordDict);
        boolean result2 = solution.wordBreakOptimized(s, wordDict);
        boolean result3 = solution.wordBreakMemo(s, wordDict);

        // 输出结果
        System.out.println("动态规划方法结果: " + result1);
        System.out.println("优化动态规划方法结果: " + result2);
        System.out.println("记忆化递归方法结果: " + result3);
    }
}
