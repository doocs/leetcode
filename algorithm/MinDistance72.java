package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 编辑距离（LeetCode 72）- 动态规划
 *
 * 时间复杂度：O(m * n)
 * - m是word1的长度，n是word2的长度
 * - 需要填充m*n的DP表
 *
 * 空间复杂度：O(m * n)
 * - 需要m*n的DP表存储中间结果
 * - 可以优化到O(min(m,n))，但这里为了清晰起见使用完整DP表
 */
public class MinDistance72 {

    /**
     * 计算两个字符串之间的编辑距离
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i][j]`表示word1的前i个字符转换为word2的前j个字符所需的最少操作数
     * 状态转移方程：
     * 1. 如果word1[i-1] == word2[j-1]，则dp[i][j] = dp[i-1][j-1]
     * 2. 如果word1[i-1] != word2[j-1]，则dp[i][j] = min(
     *      dp[i-1][j] + 1,     // 删除word1[i-1]
     *      dp[i][j-1] + 1,     // 插入word2[j-1]
     *      dp[i-1][j-1] + 1    // 替换word1[i-1]为word2[j-1]
     *    )
     *
     * 执行过程分析（以word1="horse", word2="ros"为例）：
     *
     * 初始化DP表（m=5, n=3）：
     *     ""  r   o   s
     * ""   0  1   2   3
     * h    1
     * o    2
     * r    3
     * s    4
     * e    5
     *
     * 填充DP表：
     *     ""  r   o   s
     * ""   0  1   2   3
     * h    1  1   2   3
     * o    2  2   1   2
     * r    3  2   2   2
     * s    4  3   3   2
     * e    5  4   4   3
     *
     * 填充过程详解：
     * dp[1][1]: h!=r, min(dp[0][1]+1, dp[1][0]+1, dp[0][0]+1) = min(2,2,1) = 1
     * dp[2][2]: o==o, dp[1][1] = 1
     * dp[3][1]: r==r, dp[2][0] = 2
     * ...
     *
     * 最终结果：dp[5][3] = 3
     *
     * 时间复杂度分析：
     * - 初始化DP表边界：O(m + n)
     * - 填充DP表：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(m * n)
     *
     * @param word1 源字符串
     * @param word2 目标字符串
     * @return 编辑距离
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 创建DP表，dp[i][j]表示word1前i个字符转换为word2前j个字符的最少操作数
        int[][] dp = new int[m + 1][n + 1];

        // 初始化边界条件
        // 空字符串转换为word2的前j个字符需要j次插入操作
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // word1的前i个字符转换为空字符串需要i次删除操作
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 字符相同，不需要额外操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 字符不同，需要进行一次操作（插入、删除或替换）
                    dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j],     // 删除
                                 dp[i][j - 1]),    // 插入
                        dp[i - 1][j - 1]           // 替换
                    ) + 1;
                }
            }
        }

        // 返回最终的编辑距离
        return dp[m][n];
    }

    /**
     * 空间优化版本：只使用两行数组
     *
     * 算法思路：
     * 观察DP状态转移方程，发现计算当前行只需要前一行的数据，
     * 因此可以只使用两行数组来存储数据，从而优化空间复杂度
     *
     * 时间复杂度分析：
     * - 初始化：O(n)
     * - 填充DP表：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - 只使用两行数组：O(n)
     *
     * @param word1 源字符串
     * @param word2 目标字符串
     * @return 编辑距离
     */
    public int minDistanceOptimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 只需要两行数组
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        // 初始化第一行
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            curr[0] = i;  // 初始化当前行的第一个元素

            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = Math.min(
                        Math.min(prev[j],      // 删除
                                 curr[j - 1]), // 插入
                        prev[j - 1]            // 替换
                    ) + 1;
                }
            }

            // 交换prev和curr
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

    /**
     * 辅助方法：读取用户输入的字符串
     *
     * 时间复杂度分析：
     * - 读取和处理输入：O(1)
     *
     * 空间复杂度分析：
     * - 存储两个字符串：O(m + n)
     *
     * @return 用户输入的两个字符串数组
     */
    public static String[] readWords() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个字符串: ");
        String word1 = scanner.nextLine();
        System.out.print("请输入第二个字符串: ");
        String word2 = scanner.nextLine();

        return new String[]{word1, word2};
    }

    /**
     * 主函数：处理用户输入并计算编辑距离
     */
    public static void main(String[] args) {
        System.out.println("编辑距离计算");

        // 读取用户输入的字符串
        String[] words = readWords();
        String word1 = words[0];
        String word2 = words[1];

        System.out.println("第一个字符串: \"" + word1 + "\"");
        System.out.println("第二个字符串: \"" + word2 + "\"");

        // 计算编辑距离
        MinDistance72 solution = new MinDistance72();
        int distance1 = solution.minDistance(word1, word2);
        int distance2 = solution.minDistanceOptimized(word1, word2);

        // 输出结果
        System.out.println("标准动态规划方法编辑距离: " + distance1);
        System.out.println("空间优化方法编辑距离: " + distance2);
    }
}
