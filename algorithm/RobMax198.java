package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 打家劫舍（LeetCode 198）- 动态规划
 *
 * 时间复杂度：O(n)
 * - n是房屋数量
 * - 需要遍历数组一次
 *
 * 空间复杂度：O(n) 或 O(1)
 * - 标准DP解法需要O(n)空间存储DP数组
 * - 空间优化版本只需要O(1)空间
 */
public class RobMax198 {

    /**
     * 计算能偷窃到的最高金额
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示到第i个房屋时能偷窃到的最高金额
     * 状态转移方程：
     * `dp[i] = max(dp[i-1], dp[i-2] + nums[i])`
     * （要么不偷第i个房屋，金额为`dp[i-1]`；要么偷第i个房屋，金额为`dp[i-2]` + `nums[i]`）
     *
     * 边界条件：
     * `dp[0]` = `nums[0]`（只有一个房屋）
     * `dp[1]` = max(`nums[0]`, `nums[1]`)（有两个房屋，选择金额较高的）
     *
     * 执行过程分析（以`nums=[2,7,9,3,1]`为例）：
     *
     * 初始化：
     * dp[0] = 2
     * dp[1] = max(2, 7) = 7
     *
     * 填充DP数组：
     * dp[2] = max(dp[1], dp[0] + nums[2]) = max(7, 2+9) = max(7, 11) = 11
     * dp[3] = max(dp[2], dp[1] + nums[3]) = max(11, 7+3) = max(11, 10) = 11
     * dp[4] = max(dp[3], dp[2] + nums[4]) = max(11, 11+1) = max(11, 12) = 12
     *
     * 最优策略：偷第1、3、5个房屋（金额2+9+1=12）
     *
     * 时间复杂度分析：
     * - 边界情况处理：O(1)
     * - 初始化DP数组：O(1)
     * - 填充DP数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     *
     * @param nums 每个房屋存放金额的数组
     * @return 能偷窃到的最高金额
     */
    public int rob(int[] nums) {
        // 边界情况处理
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        // 创建DP数组，dp[i]表示到第i个房屋时能偷窃到的最高金额
        int[] dp = new int[nums.length];

        // 初始化边界条件
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 填充DP数组
        for (int i = 2; i < nums.length; i++) {
            // 状态转移方程：
            // 要么不偷第i个房屋（dp[i-1]）
            // 要么偷第i个房屋（dp[i-2] + nums[i]）
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // 返回到最后一个房屋时能偷窃到的最高金额
        return dp[nums.length - 1];
    }

    /**
     * 空间优化版本：只使用两个变量
     *
     * 算法思路：
     * 观察状态转移方程，发现每次只需要前两个状态值
     * 可以用两个变量代替整个DP数组
     *
     * 时间复杂度分析：
     * - 边界情况处理：O(1)
     * - 初始化变量：O(1)
     * - 循环计算：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 每个房屋存放金额的数组
     * @return 能偷窃到的最高金额
     */
    public int robOptimized(int[] nums) {
        // 边界情况处理
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // 只需要两个变量存储前两个状态
        int prev2 = nums[0];                    // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        // 如果只有两个房屋，直接返回较大值
        if (nums.length == 2) return prev1;

        // 从第三个房屋开始计算
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * 方法3：另一种DP思路
     *
     * 算法思路：
     * 定义两个状态：
     * - `rob[i]`：偷第i个房屋时能获得的最大金额
     * - `notRob[i]`：不偷第i个房屋时能获得的最大金额
     *
     * 状态转移方程：
     * `rob[i]` = `notRob[i-1]` + `nums[i]`
     * `notRob[i]` = max(`rob[i-1]`, `notRob[i-1]`)
     *
     * 时间复杂度分析：
     * - 边界情况处理：O(1)
     * - 初始化状态：O(1)
     * - 循环计算：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 每个房屋存放金额的数组
     * @return 能偷窃到的最高金额
     */
    public int robAlternative(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int rob = nums[0];      // 偷第一个房屋
        int notRob = 0;         // 不偷第一个房屋

        for (int i = 1; i < nums.length; i++) {
            int newRob = notRob + nums[i];      // 偷当前房屋
            int newNotRob = Math.max(rob, notRob); // 不偷当前房屋
            rob = newRob;
            notRob = newNotRob;
        }

        return Math.max(rob, notRob);
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * 时间复杂度分析：
     * - 读取和处理输入：O(n)
     *
     * 空间复杂度分析：
     * - 存储数组：O(n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入每个房屋的金额（用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] nums = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            nums[i] = Integer.parseInt(strArray[i]);
        }

        return nums;
    }

    /**
     * 主函数：处理用户输入并计算最大偷窃金额
     */
    public static void main(String[] args) {
        System.out.println("打家劫舍问题");

        // 读取用户输入的数组
        int[] nums = readArray();
        System.out.println("房屋金额: " + Arrays.toString(nums));

        // 计算最大偷窃金额
        RobMax198 solution = new RobMax198();
        int result1 = solution.rob(nums);
        int result2 = solution.robOptimized(nums);
        int result3 = solution.robAlternative(nums);

        // 输出结果
        System.out.println("标准动态规划方法结果: " + result1);
        System.out.println("空间优化方法结果: " + result2);
        System.out.println("状态机方法结果: " + result3);
    }
}
