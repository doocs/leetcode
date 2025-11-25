package com.funian.algorithm.algorithm;

/**
 * 跳跃游戏（LeetCode 55）
 *
 * 时间复杂度：O(n)
 * - n是数组长度
 * - 只需要遍历数组一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
import java.util.Scanner;
import java.util.Arrays;

public class CanSkip55 {

    /**
     * 主函数：处理用户输入并判断是否能跳跃到最后一个下标
     *
     * 算法流程：
     * 1. 读取用户输入的跳跃数组
     * 2. 调用 [canJump](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/CanSkip55.java#L97-L127)方法判断是否能到达最后一个下标
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入跳跃数组（以空格分隔）：");
        String line = scanner.nextLine();
        String[] strNums = line.split(" ");
        int n = strNums.length;
        int[] nums = new int[n];

        // 将输入的字符串转换为整数数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }

        boolean canReach = canJump(nums);
        System.out.println("是否能够到达最后一个下标：" + canReach);
    }

    /**
     * 判断是否能跳跃到最后一个下标
     *
     * 算法思路：
     * 贪心算法，维护一个变量`maxReachable`表示当前能到达的最远位置
     * 遍历数组，对于每个位置i：
     * 1. 如果i > maxReachable，说明无法到达位置i，返回false
     * 2. 更新maxReachable = max(maxReachable, i + nums[i])
     * 3. 如果maxReachable >= n-1，说明已经能到达最后一个下标，返回true
     *
     * 执行过程分析（以nums=[2,3,1,1,4]为例）：
     *
     * 初始状态：
     * maxReachable = 0
     * n = 5, 最后下标 = 4
     *
     * 遍历过程：
     * i=0, nums[0]=2:
     *   i=0 <= maxReachable=0，可以到达
     *   maxReachable = max(0, 0+2) = 2
     *   maxReachable=2 < 4，继续遍历
     *
     * i=1, nums[1]=3:
     *   i=1 <= maxReachable=2，可以到达
     *   maxReachable = max(2, 1+3) = 4
     *   maxReachable=4 >= 4，可以到达最后下标，返回true
     *
     * 执行过程分析（以nums=[3,2,1,0,4]为例）：
     *
     * 初始状态：
     * maxReachable = 0
     * n = 5, 最后下标 = 4
     *
     * 遍历过程：
     * i=0, nums[0]=3:
     *   i=0 <= maxReachable=0，可以到达
     *   maxReachable = max(0, 0+3) = 3
     *   maxReachable=3 < 4，继续遍历
     *
     * i=1, nums[1]=2:
     *   i=1 <= maxReachable=3，可以到达
     *   maxReachable = max(3, 1+2) = 3
     *   maxReachable=3 < 4，继续遍历
     *
     * i=2, nums[2]=1:
     *   i=2 <= maxReachable=3，可以到达
     *   maxReachable = max(3, 2+1) = 3
     *   maxReachable=3 < 4，继续遍历
     *
     * i=3, nums[3]=0:
     *   i=3 <= maxReachable=3，可以到达
     *   maxReachable = max(3, 3+0) = 3
     *   maxReachable=3 < 4，继续遍历
     *
     * i=4, nums[4]=4:
     *   i=4 > maxReachable=3，无法到达位置4，返回false
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 跳跃数组，nums[i]表示在下标i处可以跳跃的最大长度
     * @return 如果能到达最后一个下标返回true，否则返回false
     */
    public static boolean canJump(int[] nums) {
        // 最远可达位置，初始为0（起始位置）
        int maxReachable = 0;
        int n = nums.length;

        // 遍历数组中的每个位置
        for (int i = 0; i < n; i++) {
            // 如果当前位置超过最远可达位置，说明无法到达
            // 这是关键的边界条件判断
            if (i > maxReachable) {
                return false;
            }

            // 更新最远可达位置
            // 从位置i最远可以跳到 i + nums[i]
            maxReachable = Math.max(maxReachable, i + nums[i]);

            // 如果已经可以到达最后一个下标，直接返回 true
            // 这是一个优化的边界条件，提前结束
            if (maxReachable >= n - 1) {
                return true;
            }
        }

        // 遍历完仍未到达最后一个下标（理论上不会执行到这里，因为循环中会返回）
        return false;
    }


    /**
     * 方法2：从后往前的贪心算法
     *
     * 算法思路：
     * 从最后一个位置开始，向前寻找能到达当前目标位置的最近位置
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 跳跃数组
     * @return 如果能到达最后一个下标返回true，否则返回false
     */
    public boolean canJumpReverse(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        int target = nums.length - 1; // 目标位置

        // 从倒数第二个位置开始向前遍历
        for (int i = nums.length - 2; i >= 0; i--) {
            // 如果从位置i能跳到目标位置，则更新目标位置
            if (i + nums[i] >= target) {
                target = i;
            }
        }

        // 如果目标位置能更新到起始位置，说明可以到达
        return target == 0;
    }

    /**
     * 方法3：动态规划解法
     *
     * 算法思路：
     * dp[i]表示是否能到达位置i
     *
     * 时间复杂度分析：
     * - 外层循环：O(n)
     * - 内层循环：O(nums[i])
     * - 最坏情况总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - dp数组：O(n)
     *
     * @param nums 跳跃数组
     * @return 如果能到达最后一个下标返回true，否则返回false
     */
    public boolean canJumpDP(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        boolean[] dp = new boolean[nums.length];
        dp[0] = true; // 起始位置总是可达的

        for (int i = 0; i < nums.length; i++) {
            if (!dp[i]) continue; // 如果位置i不可达，跳过

            // 从位置i可以跳到的所有位置都标记为可达
            for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
                dp[i + j] = true;
            }

            // 如果最后一个位置可达，提前返回
            if (dp[nums.length - 1]) {
                return true;
            }
        }

        return dp[nums.length - 1];
    }
}
