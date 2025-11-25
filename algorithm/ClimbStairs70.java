package com.funian.algorithm.algorithm;

/**
 * 爬楼梯（LeetCode 70）- 动态规划/斐波那契数列
 *
 * 时间复杂度：O(n)
 * - 需要计算从第3阶到第n阶
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

public class ClimbStairs70 {

    /**
     * 主函数：处理用户输入并计算爬楼梯的方法数
     *
     * 算法流程：
     * 1. 读取用户输入的楼梯阶数
     * 2. 调用 [climbStairs](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/ClimbStairs70.java#L75-L92)方法计算到达楼顶的方法数
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入楼梯的阶数
        System.out.print("输入楼梯的阶数 n: ");
        int n = scanner.nextInt();

        // 计算到达楼顶的方法数
        int ways = climbStairs(n);
        System.out.println("到达楼顶的方法数是: " + ways);

        // 演示其他解法
        ClimbStairs70 solution = new ClimbStairs70();
        int ways2 = solution.climbStairsDP(n);
        int ways3 = solution.climbStairsMemo(n);
        System.out.println("动态规划版本结果: " + ways2);
        System.out.println("记忆化递归版本结果: " + ways3);
    }

    /**
     * 计算爬楼梯的不同方法数
     *
     * 算法思路：
     * 这是一个经典的斐波那契数列问题
     * 定义f(n)表示爬到第n阶楼梯的方法数
     * 状态转移方程：
     * f(n) = f(n-1) + f(n-2)
     * （到达第n阶可以从第n-1阶爬1步，或从第n-2阶爬2步）
     *
     * 边界条件：
     * f(1) = 1（1阶楼梯只有1种方法）
     * f(2) = 2（2阶楼梯有2种方法：1+1或2）
     *
     * 执行过程分析（以n=5为例）：
     *
     * f(1) = 1
     * f(2) = 2
     * f(3) = f(2) + f(1) = 2 + 1 = 3
     * f(4) = f(3) + f(2) = 3 + 2 = 5
     * f(5) = f(4) + f(3) = 5 + 3 = 8
     *
     * 详细路径分析（n=3）：
     * 方法1: 1 + 1 + 1（每次爬1阶）
     * 方法2: 1 + 2（先爬1阶，再爬2阶）
     * 方法3: 2 + 1（先爬2阶，再爬1阶）
     * 总计：3种方法
     *
     * 时间复杂度分析：
     * - 循环计算：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param n 楼梯的阶数
     * @return 到达楼顶的方法数
     */
    public static int climbStairs(int n) {
        // 边界情况：1阶和2阶的特例
        if (n <= 2) return n;

        // 使用两个变量优化空间复杂度
        int first = 1;  // 到达第1阶的方法数
        int second = 2; // 到达第2阶的方法数
        int current = 0;

        // 动态规划，从第3阶开始计算
        for (int i = 3; i <= n; i++) {
            current = first + second; // 当前阶数的方法数
            first = second;           // 更新前一个值
            second = current;         // 更新当前值
        }

        return second; // 返回到达第n阶的方法数
    }

    /**
     * 方法2：标准动态规划解法
     *
     * 算法思路：
     * 使用动态规划数组存储每阶楼梯的方法数
     *
     * 时间复杂度分析：
     * - 初始化DP数组：O(1)
     * - 填充DP数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     *
     * @param n 楼梯的阶数
     * @return 到达楼顶的方法数
     */
    public int climbStairsDP(int n) {
        if (n <= 2) return n;

        // 创建DP数组
        int[] dp = new int[n + 1];

        // 初始化边界条件
        dp[1] = 1;
        dp[2] = 2;

        // 填充DP数组
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    /**
     * 方法3：记忆化递归解法
     *
     * 算法思路：
     * 使用递归方式计算方法数，并通过记忆化数组避免重复计算
     *
     * 时间复杂度分析：
     * - 每个子问题只计算一次：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(n)
     * - 记忆化数组存储空间：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param n 楼梯的阶数
     * @return 到达楼顶的方法数
     */
    public int climbStairsMemo(int n) {
        int[] memo = new int[n + 1];
        return climbStairsHelper(n, memo);
    }

    /**
     * 记忆化递归辅助方法
     *
     * 算法思路：
     * 递归计算爬楼梯的方法数，并使用记忆化数组缓存已计算的结果
     *
     * @param n 楼梯的阶数
     * @param memo 记忆化数组
     * @return 到达楼顶的方法数
     */
    private int climbStairsHelper(int n, int[] memo) {
        if (n <= 2) return n;

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = climbStairsHelper(n - 1, memo) + climbStairsHelper(n - 2, memo);
        return memo[n];
    }
}
