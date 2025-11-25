package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 零钱兑换（LeetCode 322）- 动态规划
 *
 * 时间复杂度：O(amount * coins.length)
 * - 外层循环运行amount次
 * - 内层循环运行coins.length次
 *
 * 空间复杂度：O(amount)
 * - 需要长度为amount+1的DP数组
 */
public class CoinChange322 {

    /**
     * 计算凑成总金额所需的最少硬币个数
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示凑成金额i所需的最少硬币个数
     * 状态转移方程：
     * `dp[i] = min(dp[i - coin] + 1)` for all coin in coins where coin <= i
     *
     * 执行过程分析（以`coins=[1,3,4]`, `amount=6`为例）：
     *
     * 初始化DP数组（amount=6）：
     * dp = [0, MAX, MAX, MAX, MAX, MAX, MAX]
     *
     * 填充DP数组：
     * dp[1] = min(dp[0] + 1) = 1 （使用硬币1）
     * dp[2] = min(dp[1] + 1) = 2 （使用两个硬币1）
     * dp[3] = min(dp[2] + 1, dp[0] + 1) = 1 （使用硬币3）
     * dp[4] = min(dp[3] + 1, dp[1] + 1, dp[0] + 1) = 1 （使用硬币4）
     * dp[5] = min(dp[4] + 1, dp[2] + 1, dp[1] + 1) = 2 （使用硬币4+1）
     * dp[6] = min(dp[5] + 1, dp[3] + 1, dp[2] + 1) = 2 （使用硬币3+3）
     *
     * 最终结果：dp[6] = 2（使用两个硬币3）
     *
     * 时间复杂度分析：
     * - 初始化DP数组：O(amount)
     * - 填充DP数组：O(amount * coins.length)
     * - 总时间复杂度：O(amount * coins.length)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(amount)
     *
     * @param coins 不同面额的硬币数组
     * @param amount 总金额
     * @return 凑成总金额所需的最少硬币个数，如果无法凑成返回-1
     */
    public int coinChange(int[] coins, int amount) {
        // 边界情况：金额为0时不需要硬币
        if (amount == 0) return 0;

        // dp[i] 表示凑成金额i所需的最少硬币个数
        int[] dp = new int[amount + 1];

        // 初始化DP数组为一个大值（表示无法凑成）
        Arrays.fill(dp, amount + 1);
        dp[0] = 0; // 凑成金额0需要0个硬币

        // 动态规划填充DP数组
        for (int i = 1; i <= amount; i++) {
            // 尝试使用每种硬币
            for (int coin : coins) {
                if (coin <= i) {
                    // 状态转移方程：
                    // 凑成金额i的最少硬币数 = min(凑成金额(i-coin)的最少硬币数 + 1)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 如果dp[amount]仍为初始值，说明无法凑成
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 方法2：BFS解法
     *
     * 算法思路：
     * 将问题看作图论问题，每个金额是一个节点
     * 如果两个金额相差一个硬币面额，则它们之间有边
     * 使用BFS找到从0到amount的最短路径
     *
     * 时间复杂度分析：
     * - BFS遍历：O(amount * coins.length)
     * - 队列操作：O(1)
     * - 总时间复杂度：O(amount * coins.length)
     *
     * 空间复杂度分析：
     * - 队列存储空间：O(amount)
     * - visited数组存储空间：O(amount)
     *
     * @param coins 不同面额的硬币数组
     * @param amount 总金额
     * @return 凑成总金额所需的最少硬币个数，如果无法凑成返回-1
     */
    public int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;

        // 使用BFS寻找最短路径
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        queue.offer(0);
        visited[0] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // 尝试添加每种硬币
                for (int coin : coins) {
                    int next = current + coin;

                    if (next == amount) {
                        return level;
                    }

                    if (next < amount && !visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 方法3：记忆化递归解法
     *
     * 算法思路：
     * 使用递归方式计算凑成指定金额所需的最少硬币数，
     * 并通过记忆化数组避免重复计算相同子问题
     *
     * 时间复杂度分析：
     * - 每个子问题只计算一次：O(amount)
     * - 每个子问题需要尝试所有硬币：O(coins.length)
     * - 总时间复杂度：O(amount * coins.length)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(amount)
     * - 记忆化数组存储空间：O(amount)
     *
     * @param coins 不同面额的硬币数组
     * @param amount 总金额
     * @return 凑成总金额所需的最少硬币个数，如果无法凑成返回-1
     */
    public int coinChangeMemo(int[] coins, int amount) {
        if (amount == 0) return 0;

        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2); // -2表示未计算，-1表示无法凑成

        return coinChangeHelper(coins, amount, memo);
    }

    /**
     * 记忆化递归辅助方法
     *
     * 算法思路：
     * 递归地计算凑成指定金额所需的最少硬币数
     *
     * @param coins 不同面额的硬币数组
     * @param amount 剩余金额
     * @param memo 记忆化数组
     * @return 凑成剩余金额所需的最少硬币个数
     */
    private int coinChangeHelper(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        if (memo[amount] != -2) {
            return memo[amount];
        }

        int minCoins = Integer.MAX_VALUE;

        // 尝试使用每种硬币
        for (int coin : coins) {
            int result = coinChangeHelper(coins, amount - coin, memo);
            if (result != -1) {
                minCoins = Math.min(minCoins, result + 1);
            }
        }

        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[amount];
    }

    /**
     * 辅助方法：读取用户输入的硬币数组
     *
     * 时间复杂度分析：
     * - 读取和处理输入：O(n)，n为硬币数量
     *
     * 空间复杂度分析：
     * - 存储硬币数组：O(n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readCoins() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入硬币面额（用空格分隔）：");
        String input = scanner.nextLine();
        String[] strArray = input.split(" ");

        int[] coins = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            coins[i] = Integer.parseInt(strArray[i]);
        }

        return coins;
    }

    /**
     * 主函数：处理用户输入并计算最少硬币个数
     */
    public static void main(String[] args) {
        System.out.println("零钱兑换问题");

        // 读取用户输入的硬币数组
        int[] coins = readCoins();
        System.out.println("硬币面额: " + Arrays.toString(coins));

        // 读取总金额
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入总金额: ");
        int amount = scanner.nextInt();

        // 计算最少硬币个数
        CoinChange322 solution = new CoinChange322();
        int result1 = solution.coinChange(coins, amount);
        int result2 = solution.coinChangeBFS(coins, amount);
        int result3 = solution.coinChangeMemo(coins, amount);

        // 输出结果
        System.out.println("动态规划方法结果: " + result1);
        System.out.println("BFS方法结果: " + result2);
        System.out.println("记忆化递归方法结果: " + result3);
    }
}
