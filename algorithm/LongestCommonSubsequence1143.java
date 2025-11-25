package com.funian.algorithm.algorithm;

/**
 * 最长公共子序列（LeetCode 1143）- 动态规划
 *
 * 时间复杂度：O(m * n)
 * - m是text1的长度，n是text2的长度
 * - 需要填充m*n的DP表
 *
 * 空间复杂度：O(m * n)
 * - 需要m*n的DP表存储中间结果
 * - 可以优化到O(min(m,n))，但这里为了清晰起见使用完整DP表
 */
import java.util.Scanner;
import java.util.Arrays;

public class LongestCommonSubsequence1143 {

    /**
     * 主函数：处理用户输入并计算最长公共子序列的长度
     *
     * 算法流程：
     * 1. 读取用户输入的两个字符串
     * 2. 调用 [longestCommonSubsequence](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/LongestCommonSubsequence1143.java#L117-L144)方法计算最长公共子序列的长度
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入两个字符串
        System.out.print("请输入第一个字符串 text1: ");
        String text1 = scanner.nextLine();
        System.out.print("请输入第二个字符串 text2: ");
        String text2 = scanner.nextLine();

        // 调用 longestCommonSubsequence 方法计算最长公共子序列的长度
        int result = longestCommonSubsequence(text1, text2);
        // 输出结果
        System.out.println("最长公共子序列的长度：" + result);

        // 演示如何获取实际的最长公共子序列
        LongestCommonSubsequence1143 solution = new LongestCommonSubsequence1143();
        String lcs = solution.findLCS(text1, text2);
        System.out.println("最长公共子序列内容: \"" + lcs + "\"");
    }

    /**
     * 计算两个字符串的最长公共子序列长度
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i][j]`表示text1的前i个字符和text2的前j个字符的最长公共子序列长度
     * 状态转移方程：
     * 1. 如果text1[i-1] == text2[j-1]，则dp[i][j] = dp[i-1][j-1] + 1
     * 2. 如果text1[i-1] != text2[j-1]，则dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *
     * 执行过程分析（以text1="abcde", text2="ace"为例）：
     *
     * 初始化DP表（m=5, n=3）：
     *       ""  a  c  e
     *    ""  0  0  0  0
     *    a   0
     *    b   0
     *    c   0
     *    d   0
     *    e   0
     *
     * 填充DP表：
     *       ""  a  c  e
     *    ""  0  0  0  0
     *    a   0  1  1  1
     *    b   0  1  1  1
     *    c   0  1  2  2
     *    d   0  1  2  2
     *    e   0  1  2  3
     *
     * 填充过程详解：
     * dp[1][1]: a==a, dp[0][0]+1 = 1
     * dp[1][2]: a!=c, max(dp[0][2], dp[1][1]) = max(0,1) = 1
     * dp[3][2]: c==c, dp[2][1]+1 = 1+1 = 2
     * dp[5][3]: e==e, dp[4][2]+1 = 2+1 = 3
     *
     * 最终结果：dp[5][3] = 3，LCS为"ace"
     *
     * 时间复杂度分析：
     * - 初始化DP表：O(m*n)
     * - 填充DP表：O(m*n)
     * - 总时间复杂度：O(m*n)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(m*n)
     *
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(); // text1 的长度
        int n = text2.length(); // text2 的长度

        // 创建二维数组 dp，大小为 (m + 1) x (n + 1)
        // dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];

        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果字符相等，最长公共子序列长度加 1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 否则取左边或上边的最大值（分别表示不包含text1[i-1]或不包含text2[j-1]的情况）
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回最后一个元素，即最长公共子序列的长度
        return dp[m][n];
    }

    /**
     * 扩展方法：找出实际的最长公共子序列
     *
     * 算法思路：
     * 在计算完DP表后，通过回溯的方式构造出实际的LCS字符串
     * 从DP表的右下角开始，根据状态转移的路径反向构造LCS
     *
     * 时间复杂度分析：
     * - 填充DP表：O(m*n)
     * - 回溯构造LCS：O(m+n)
     * - 总时间复杂度：O(m*n)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(m*n)
     * - StringBuilder存储LCS：O(min(m,n))
     *
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列
     */
    public String findLCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 创建DP表
        int[][] dp = new int[m + 1][n + 1];

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 回溯构造LCS
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                // 字符相等，是LCS的一部分
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 上面的值更大，说明不包含text1[i-1]
                i--;
            } else {
                // 左边的值更大或相等，说明不包含text2[j-1]
                j--;
            }
        }

        // 因为是从后往前构造的，需要反转
        return lcs.reverse().toString();
    }
}
