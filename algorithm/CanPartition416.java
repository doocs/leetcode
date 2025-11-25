package com.funian.algorithm.algorithm;

/**
 * 分割等和子集（LeetCode 416）- 动态规划（0-1背包问题）
 *
 * 时间复杂度：O(n * sum)
 * - n是数组长度，sum是数组元素和的一半
 * - 需要遍历每个元素，并对每个元素更新dp数组
 *
 * 空间复杂度：O(sum)
 * - 只需要大小为sum+1的DP数组
 */
import java.util.Scanner;
import java.util.Arrays;

public class CanPartition416 {

    /**
     * 主函数：处理用户输入并判断数组是否可以分割成两个和相等的子集
     *
     * 算法流程：
     * 1. 读取用户输入的正整数数组
     * 2. 调用 [canPartition](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/CanPartition416.java#L113-L154)方法判断是否可以分割
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入正整数数组
        System.out.print("请输入正整数数组（用空格分隔）：");
        // 读取输入并以空格分割成字符串数组
        String[] input = scanner.nextLine().split(" ");

        // 将字符串数组转换为整数数组
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]); // 转换字符串为整数
        }

        // 调用 canPartition 方法判断是否可以分割
        boolean result = canPartition(nums);
        // 输出结果
        System.out.println("是否可以分割成两个和相等的子集：" + result);
    }

    /**
     * 判断数组是否可以分割成两个和相等的子集
     *
     * 算法思路：
     * 这是一个0-1背包问题的变种
     * 问题转化为：能否从数组中选出一些元素，使其和等于总和的一半
     *
     * 状态定义：
     * `dp[i]` 表示是否能选出一些元素使其和等于 i
     *
     * 状态转移：
     * 对于每个元素`num`，更新`dp`数组：
     * `dp[j] = dp[j] || dp[j - num]`
     * （要么不选`num`仍能达到和`j`，要么选`num`从和`j-num`达到和`j`）
     *
     * 执行过程分析（以`nums=[1,5,11,5]`为例）：
     *
     * 第一步：计算总和
     * totalSum = 1+5+11+5 = 22
     * target = 22/2 = 11
     *
     * 第二步：初始化DP数组
     * dp = [T, F, F, F, F, F, F, F, F, F, F, F]
     * 索引:  0  1  2  3  4  5  6  7  8  9  10 11
     *
     * 第三步：遍历每个元素更新DP数组
     *
     * 处理num=1:
     * j=11: dp[11] = dp[11] || dp[10] = F || F = F
     * ...
     * j=1: dp[1] = dp[1] || dp[0] = F || T = T
     * dp = [T, T, F, F, F, F, F, F, F, F, F, F]
     *
     * 处理num=5:
     * j=11: dp[11] = dp[11] || dp[6] = F || F = F
     * ...
     * j=6: dp[6] = dp[6] || dp[1] = F || T = T
     * j=5: dp[5] = dp[5] || dp[0] = F || T = T
     * dp = [T, T, F, F, F, T, T, F, F, F, F, F]
     *
     * 处理num=11:
     * j=11: dp[11] = dp[11] || dp[0] = F || T = T
     * dp = [T, T, F, F, F, T, T, F, F, F, F, T]
     *
     * 处理num=5:
     * j=11: dp[11] = dp[11] || dp[6] = T || T = T
     * ...
     *
     * 最终结果：dp[11] = T，可以分割
     * 一个可能的分割方案：[1,5,5] 和 [11]，和都为11
     *
     * 执行过程分析（以`nums=[1,2,3,5]`为例）：
     *
     * 第一步：计算总和
     * totalSum = 1+2+3+5 = 11（奇数）
     * 直接返回false
     *
     * 时间复杂度分析：
     * - 计算总和：O(n)
     * - 更新DP数组：O(n * sum)
     * - 总时间复杂度：O(n * sum)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(sum)
     *
     * @param nums 输入的正整数数组
     * @return 如果可以分割成两个和相等的子集返回true，否则返回false
     */
    public static boolean canPartition(int[] nums) {
        int totalSum = 0; // 初始化总和

        // 计算数组中所有元素的总和
        for (int num : nums) {
            totalSum += num; // 累加元素
        }

        // 如果总和为奇数，无法分割成两个相等的部分
        // 这是一个重要的边界条件
        if (totalSum % 2 != 0) {
            return false; // 直接返回 false
        }

        // 目标子集和（总和的一半）
        int target = totalSum / 2;

        // 创建动态规划数组
        // dp[i] 表示是否能选出一些元素使其和等于 i
        boolean[] dp = new boolean[target + 1];

        // 0 可以通过不选择任何元素得到（基础情况）
        dp[0] = true;

        // 遍历每个元素
        for (int num : nums) {
            // 从目标值开始倒序更新 dp 数组
            // 倒序是为了避免重复使用同一个元素
            // 如果正序更新，dp[j-num]可能已经在本轮中被更新过
            for (int j = target; j >= num; j--) {
                // 更新 dp[j]，表示是否可以通过当前元素达到和 j
                // dp[j] = dp[j] || dp[j - num]
                // 要么不选num仍能达到和j，要么选num从和j-num达到和j
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // 返回 dp[target]，表示是否能够通过选择元素得到目标子集和
        return dp[target];
    }

    /**
     * 方法2：二维DP解法（更直观的理解）
     *
     * 算法思路：
     * `dp[i][j]` 表示前i个元素是否能选出一些使其和等于j
     *
     * 时间复杂度分析：
     * - 初始化DP表：O(n)
     * - 填充DP表：O(n * sum)
     * - 总时间复杂度：O(n * sum)
     *
     * 空间复杂度分析：
     * - 二维DP表存储空间：O(n * sum)
     *
     * @param nums 输入的正整数数组
     * @return 如果可以分割成两个和相等的子集返回true，否则返回false
     */
    public boolean canPartition2D(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        int n = nums.length;

        // dp[i][j] 表示前i个元素是否能选出一些使其和等于j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // 基础情况：不选任何元素可以得到和0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // 不选第i个元素
                dp[i][j] = dp[i - 1][j];
                // 选第i个元素（如果可能）
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    /**
     * 扩展方法：返回实际的分割方案
     *
     * 时间复杂度分析：
     * - 计算总和：O(n)
     * - 更新DP数组：O(n * sum)
     * - 重构解：O(n)
     * - 总时间复杂度：O(n * sum)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(sum)
     * - 路径记录数组：O(n * sum)
     * - 子集存储空间：O(n)
     *
     * @param nums 输入的正整数数组
     * @return 如果可以分割则返回两个子集，否则返回null
     */
    public int[][] findPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            return null;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // 记录路径
        boolean[][] path = new boolean[nums.length][target + 1];

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) {
                    dp[j] = true;
                    path[i][j] = true;
                }
            }
        }

        if (!dp[target]) {
            return null;
        }

        // 重构解
        int[][] result = new int[2][];
        java.util.List<Integer> subset1 = new java.util.ArrayList<>();
        int currentSum = target;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (path[i][currentSum]) {
                subset1.add(nums[i]);
                currentSum -= nums[i];
            }
        }

        // 构建另一个子集
        java.util.List<Integer> subset2 = new java.util.ArrayList<>();
        boolean[] used = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (subset1.contains(nums[i])) {
                used[i] = true;
                subset1.remove(Integer.valueOf(nums[i]));
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                subset2.add(nums[i]);
            }
        }

        result[0] = subset1.stream().mapToInt(i -> i).toArray();
        result[1] = subset2.stream().mapToInt(i -> i).toArray();

        return result;
    }
}
