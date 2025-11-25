package com.funian.algorithm.algorithm;

/**
 * 最长回文子串（LeetCode 5）- 中心扩展法
 *
 * 时间复杂度：O(n²)
 * - 外层循环运行n次（n为字符串长度）
 * - 每次中心扩展最多需要O(n)时间
 * - 总时间复杂度为O(n²)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 不需要额外的数据结构存储中间结果
 */
import java.util.Scanner;

public class LongestPalindrome5 {

    /**
     * 主函数：处理用户输入并找出最长回文子串
     *
     * 算法流程：
     * 1. 读取用户输入的字符串
     * 2. 调用 [longestPalindrome](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/LongestPalindrome5.java#L77-L112)方法找出最长回文子串
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入字符串
        System.out.print("输入字符串: ");
        String s = scanner.nextLine();

        // 找到最长回文子串
        String longestPalindrome = longestPalindrome(s);
        System.out.println("最长回文子串是: " + longestPalindrome);
    }

    /**
     * 找出字符串中的最长回文子串
     *
     * 算法思路：
     * 使用中心扩展法，遍历每个可能的回文中心，向两边扩展寻找回文
     * 回文有两种情况：
     * 1. 奇数长度回文：以单个字符为中心
     * 2. 偶数长度回文：以两个字符之间为中心
     *
     * 执行过程分析（以字符串 "babad" 为例）：
     *
     * 遍历每个字符作为中心：
     * i=0, 字符'b':
     *   奇数回文：以'b'为中心，扩展得到"b"，长度1
     *   偶数回文：以'b'和'a'之间为中心，无法扩展，长度0
     *   当前最长回文："b"，长度1
     *
     * i=1, 字符'a':
     *   奇数回文：以'a'为中心，无法扩展，长度1
     *   偶数回文：以'a'和'b'之间为中心，无法扩展，长度0
     *   当前最长回文："b"，长度1
     *
     * i=2, 字符'b':
     *   奇数回文：以'b'为中心，向两边扩展得到"bab"，长度3
     *   偶数回文：以'b'和'a'之间为中心，无法扩展，长度0
     *   更新最长回文："bab"，长度3
     *
     * i=3, 字符'a':
     *   奇数回文：以'a'为中心，无法扩展，长度1
     *   偶数回文：以'a'和'd'之间为中心，无法扩展，长度0
     *   当前最长回文："bab"，长度3
     *
     * i=4, 字符'd':
     *   奇数回文：以'd'为中心，扩展得到"d"，长度1
     *   偶数回文：无
     *   当前最长回文："bab"，长度3
     *
     * 再以字符串 "cbbd" 为例：
     *
     * i=0, 字符'c': 最长回文"c"，长度1
     * i=1, 字符'b':
     *   奇数回文："b"，长度1
     *   偶数回文：以'b'和'b'之间为中心，扩展得到"bb"，长度2
     *   更新最长回文："bb"，长度2
     * i=2, 字符'b': 最长回文仍为"bb"，长度2
     * i=3, 字符'd': 最长回文仍为"bb"，长度2
     *
     * 时间复杂度分析：
     * - 外层循环运行n次（n为字符串长度）：O(n)
     * - 每次中心扩展最多需要O(n)时间：O(n)
     * - 总时间复杂度为O(n²)
     *
     * 空间复杂度分析：
     * - 只使用了常数个额外变量：O(1)
     * - 不需要额外的数据结构存储中间结果：O(1)
     *
     * @param s 输入字符串
     * @return 最长回文子串
     */
    public static String longestPalindrome(String s) {
        // 边界情况：空字符串或长度为0的字符串
        if (s == null || s.length() < 1) return "";

        // 记录最长回文子串的起始和结束位置
        int start = 0, end = 0;

        // 遍历每个字符作为回文中心
        for (int i = 0; i < s.length(); i++) {
            // 尝试以 s[i] 为中心的奇数长度回文
            int len1 = expandAroundCenter(s, i, i);
            // 尝试以 s[i] 和 s[i+1] 为中心的偶数长度回文
            int len2 = expandAroundCenter(s, i, i + 1);

            // 取两种情况下的最大长度
            int len = Math.max(len1, len2);

            // 如果当前回文长度大于已记录的最长回文长度，更新起始和结束位置
            if (len > end - start) {
                // 计算新的起始位置
                start = i - (len - 1) / 2;
                // 计算新的结束位置
                end = i + len / 2;
            }
        }

        // 返回最长回文子串
        return s.substring(start, end + 1);
    }

    /**
     * 从中心向两边扩展寻找回文
     *
     * 算法思路：
     * 从给定的中心位置向两边扩展，直到字符不匹配或到达边界
     *
     * 时间复杂度分析：
     * - 最多扩展n次：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param s 输入字符串
     * @param left 左边界
     * @param right 右边界
     * @return 回文的长度
     */
    private static int expandAroundCenter(String s, int left, int right) {
        // 当左右字符相等且边界有效时，继续向外扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;   // 左边界左移
            right++;  // 右边界右移
        }
        // 返回回文的长度
        return right - left - 1;
    }

    /**
     * 方法2：动态规划解法
     *
     * 算法思路：
     * 使用二维DP表，dp[i][j]表示s[i...j]是否为回文
     * 状态转移方程：
     * dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
     *
     * 时间复杂度分析：
     * - 初始化单字符回文：O(n)
     * - 检查长度为2的子串：O(n)
     * - 检查长度大于2的子串：O(n²)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 二维DP表：O(n²)
     *
     * @param s 输入字符串
     * @return 最长回文子串
     */
    public static String longestPalindromeDP(String s) {
        if (s == null || s.length() < 1) return "";

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLength = 1;

        // 单个字符都是回文
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 检查长度为2的子串
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // 检查长度大于2的子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }
}
