package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 最长递增子序列（LeetCode 300）- 动态规划
 *
 * 时间复杂度：
 * - 方法1（动态规划）：O(n²)
 *   外层循环运行n次，内层循环最多运行i次
 * - 方法2（二分查找优化）：O(n log n)
 *   外层循环运行n次，每次二分查找耗时O(log n)
 *
 * 空间复杂度：O(n)
 * - 需要长度为n的DP数组或tails数组
 */
public class LengthOfLIS300 {

    /**
     * 主函数：处理用户输入并计算最长递增子序列的长度
     *
     * 算法流程：
     * 1. 读取用户输入的整数数组
     * 2. 调用 [lengthOfLIS](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/LengthOfLIS300.java#L141-L182)方法计算最长递增子序列的长度
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入数组
        System.out.print("请输入整数数组（用空格分隔）：");
        String[] input = scanner.nextLine().split(" ");
        int[] nums = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        int result = lengthOfLIS(nums);
        System.out.println("最长严格递增子序列的长度是：" + result);

        // 演示其他解法
        LengthOfLIS300 solution = new LengthOfLIS300();
        int result2 = solution.lengthOfLISBinarySearch(nums);
        System.out.println("二分查找优化方法结果：" + result2);
    }

    /**
     * 计算最长严格递增子序列的长度
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示以`nums[i]`结尾的最长严格递增子序列的长度
     * 状态转移方程：
     * `dp[i] = max(dp[j] + 1)` for all j where 0 <= j < i and `nums[j]` < `nums[i]`
     *
     * 执行过程分析（以`nums=[10,9,2,5,3,7,101,18]`为例）：
     *
     * 初始化DP数组：
     * dp = [1, 1, 1, 1, 1, 1, 1, 1]
     * 索引: 0  1  2  3  4  5   6   7
     * 值:  10 9  2  5  3  7  101  18
     *
     * 填充DP数组：
     * i=1, `nums[1]=9`:
     *   j=0, `nums[0]=10`: 9 < 10，不满足条件
     *   dp[1] = 1
     *
     * i=2, `nums[2]=2`:
     *   j=0, `nums[0]=10`: 2 < 10，不满足条件
     *   j=1, `nums[1]=9`: 2 < 9，不满足条件
     *   dp[2] = 1
     *
     * i=3, `nums[3]=5`:
     *   j=0, `nums[0]=10`: 5 < 10，不满足条件
     *   j=1, `nums[1]=9`: 5 < 9，不满足条件
     *   j=2, `nums[2]=2`: 5 > 2，满足条件，dp[3] = max(1, dp[2]+1) = max(1, 2) = 2
     *
     * i=4, `nums[4]=3`:
     *   j=0, `nums[0]=10`: 3 < 10，不满足条件
     *   j=1, `nums[1]=9`: 3 < 9，不满足条件
     *   j=2, `nums[2]=2`: 3 > 2，满足条件，dp[4] = max(1, dp[2]+1) = max(1, 2) = 2
     *   j=3, `nums[3]=5`: 3 < 5，不满足条件
     *
     * i=5, `nums[5]=7`:
     *   j=0, `nums[0]=10`: 7 < 10，不满足条件
     *   j=1, `nums[1]=9`: 7 < 9，不满足条件
     *   j=2, `nums[2]=2`: 7 > 2，满足条件，dp[5] = max(1, dp[2]+1) = max(1, 2) = 2
     *   j=3, `nums[3]=5`: 7 > 5，满足条件，dp[5] = max(2, dp[3]+1) = max(2, 3) = 3
     *   j=4, `nums[4]=3`: 7 > 3，满足条件，dp[5] = max(3, dp[4]+1) = max(3, 3) = 3
     *
     * i=6, `nums[6]=101`:
     *   j=0到j=5中，所有`nums[j]` < 101
     *   dp[6] = max(dp[0到5]) + 1 = max(1,1,1,2,2,3) + 1 = 4
     *
     * i=7, `nums[7]=18`:
     *   j=0, `nums[0]=10`: 18 > 10，满足条件
     *   j=5, `nums[5]=7`: 18 > 7，满足条件，dp[7] = max(..., dp[5]+1) = max(..., 4) = 4
     *   j=6, `nums[6]=101`: 18 < 101，不满足条件
     *
     * 最终DP数组：
     * dp = [1, 1, 1, 2, 2, 3, 4, 4]
     *
     * 最长递增子序列长度：max(dp) = 4
     * 一个可能的最长递增子序列：[2, 3, 7, 18] 或 [2, 3, 7, 101]
     *
     * 时间复杂度分析：
     * - 初始化DP数组：O(n)
     * - 填充DP数组：O(n²)
     * - 查找最大值：O(n)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     *
     * @param nums 输入的整数数组
     * @return 最长严格递增子序列的长度
     */
    public static int lengthOfLIS(int[] nums) {
        // 边界情况：空数组
        if (nums.length == 0) return 0;

        // dp[i] 表示以 nums[i] 结尾的最长严格递增子序列的长度
        int[] dp = new int[nums.length];

        // 初始化 dp 数组
        // 每个元素自身就是一个长度为 1 的子序列
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        // 动态规划填充 dp 数组
        // 外层循环遍历每个元素
        for (int i = 1; i < nums.length; i++) {
            // 内层循环检查所有在i之前的元素
            for (int j = 0; j < i; j++) {
                // 只考虑严格递增的情况：nums[i] > nums[j]
                if (nums[i] > nums[j]) {
                    // 状态转移方程：
                    // 以nums[i]结尾的最长递增子序列长度 =
                    // max(当前值, 以nums[j]结尾的最长递增子序列长度 + 1)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 找到 dp 数组中的最大值，即为最长递增子序列的长度
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    /**
     * 方法2：二分查找优化解法
     *
     * 算法思路：
     * 维护一个数组`tails`，`tails[i]`表示长度为i+1的递增子序列的最小尾部元素
     * 使用二分查找来找到插入位置
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 每次二分查找：O(log n)
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - tails数组存储空间：O(n)
     *
     * @param nums 输入的整数数组
     * @return 最长严格递增子序列的长度
     */
    public int lengthOfLISBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // tails[i] 表示长度为 i+1 的递增子序列的最小尾部元素
        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            // 使用二分查找找到num应该插入的位置
            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 更新tails数组
            tails[left] = num;

            // 如果插入位置在数组末尾，说明递增子序列长度增加
            if (left == size) {
                size++;
            }
        }

        return size;
    }

    /**
     * 扩展方法：返回实际的最长递增子序列
     *
     * 算法思路：
     * 在计算最长递增子序列长度的同时，记录每个元素的前驱元素，
     * 最后通过回溯前驱元素重构最长递增子序列
     *
     * 时间复杂度分析：
     * - 计算DP值和前驱：O(n²)
     * - 重构子序列：O(n)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     * - 前驱数组存储空间：O(n)
     * - 结果数组存储空间：O(k)，k为最长递增子序列长度
     *
     * @param nums 输入的整数数组
     * @return 最长递增子序列
     */
    public int[] findLIS(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLength = 1;
        int maxIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        // 重构最长递增子序列
        int[] result = new int[maxLength];
        int index = maxIndex;
        for (int i = maxLength - 1; i >= 0; i--) {
            result[i] = nums[index];
            index = prev[index];
        }

        return result;
    }
}
