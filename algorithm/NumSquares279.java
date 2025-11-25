package com.funian.algorithm.algorithm;

/**
 * 完全平方数（LeetCode 279）- 动态规划
 *
 * 时间复杂度：O(n * √n)
 * - 外层循环运行n次
 * - 内层循环最多运行√n次（j*j ≤ i）
 *
 * 空间复杂度：O(n)
 * - 需要长度为n+1的DP数组
 */
import java.util.Scanner;
import java.util.Arrays;

public class NumSquares279 {

    /**
     * 主函数：处理用户输入并计算完全平方数的最少数量
     *
     * 算法流程：
     * 1. 读取用户输入的整数n
     * 2. 调用 [numSquares](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/NumSquares279.java#L107-L134)方法计算完全平方数的最少数量
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个整数 n：");
        int n = scanner.nextInt();

        int result = numSquares(n);
        System.out.println("和为 " + n + " 的完全平方数的最少数量是：" + result);

        // 演示其他解法
        NumSquares279 solution = new NumSquares279();
        int result2 = solution.numSquaresBFS(n);
        System.out.println("BFS方法结果: " + result2);
    }

    /**
     * 计算和为n的完全平方数的最少数量
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i]`表示和为i的完全平方数的最少数量
     * 状态转移方程：
     * `dp[i] = min(dp[i - j*j] + 1)` for all j where j*j <= i
     *
     * 执行过程分析（以n=12为例）：
     *
     * 初始化DP数组：
     * dp = [0, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX]
     *
     * 填充DP数组：
     * dp[1] = min(dp[0] + 1) = 1 （1 = 1²）
     * dp[2] = min(dp[1] + 1) = 2 （2 = 1² + 1²）
     * dp[3] = min(dp[2] + 1) = 3 （3 = 1² + 1² + 1²）
     * dp[4] = min(dp[3] + 1, dp[0] + 1) = 1 （4 = 2²）
     * dp[5] = min(dp[4] + 1, dp[1] + 1) = 2 （5 = 2² + 1²）
     * dp[6] = min(dp[5] + 1, dp[2] + 1) = 3 （6 = 2² + 1² + 1²）
     * dp[7] = min(dp[6] + 1, dp[3] + 1) = 4 （7 = 2² + 1² + 1² + 1²）
     * dp[8] = min(dp[7] + 1, dp[4] + 1) = 2 （8 = 2² + 2²）
     * dp[9] = min(dp[8] + 1, dp[5] + 1, dp[0] + 1) = 1 （9 = 3²）
     * dp[10] = min(dp[9] + 1, dp[6] + 1, dp[1] + 1) = 2 （10 = 3² + 1²）
     * dp[11] = min(dp[10] + 1, dp[7] + 1, dp[2] + 1) = 3 （11 = 3² + 1² + 1²）
     * dp[12] = min(dp[11] + 1, dp[8] + 1, dp[3] + 1) = 3 （12 = 2² + 2² + 2²）
     *
     * 最终结果：dp[12] = 3
     *
     * 时间复杂度分析：
     * - 初始化DP数组：O(n)
     * - 填充DP数组：O(n * √n)
     * - 总时间复杂度：O(n * √n)
     *
     * 空间复杂度分析：
     * - DP数组存储空间：O(n)
     *
     * @param n 目标整数
     * @return 和为n的完全平方数的最少数量
     */
    public static int numSquares(int n) {
        // dp[i] 表示和为 i 的完全平方数的最少数量
        int[] dp = new int[n + 1];

        // 初始化 dp 数组，设置为最大值
        // dp[0] = 0（和为0需要0个完全平方数）
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 动态规划填充 dp 数组
        for (int i = 1; i <= n; i++) {
            // 尝试所有可能的完全平方数 j*j，其中 j*j <= i
            for (int j = 1; j * j <= i; j++) {
                // 状态转移方程：
                // 和为i的最少数量 = min(和为(i-j*j)的最少数量 + 1)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n]; // 返回和为 n 的最少数量
    }


    /**
     * 方法2：广度优先搜索（BFS）解法
     *
     * 算法思路：
     * 将问题看作图论问题，每个数字是一个节点
     * 如果两个数字相差一个完全平方数，则它们之间有边
     * 使用BFS找到从n到0的最短路径
     *
     * 时间复杂度分析：
     * - BFS遍历：O(n * √n)
     * - 队列操作：O(1)
     * - 总时间复杂度：O(n * √n)
     *
     * 空间复杂度分析：
     * - 队列存储空间：O(n)
     * - visited数组存储空间：O(n)
     *
     * @param n 目标整数
     * @return 和为n的完全平方数的最少数量
     */
    public int numSquaresBFS(int n) {
        // 使用BFS寻找最短路径
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(n);
        visited[n] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // 尝试减去所有可能的完全平方数
                for (int j = 1; j * j <= current; j++) {
                    int next = current - j * j;

                    if (next == 0) {
                        return level;
                    }

                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return level;
    }

    /**
     * 方法3：数学解法（四平方和定理）
     *
     * 算法思路：
     * 根据拉格朗日四平方和定理，任何自然数都可以表示为四个整数的平方和
     * 根据勒让德三平方和定理，如果n不等于4^k(8m+7)的形式，则可以表示为三个平方数之和
     *
     * 时间复杂度分析：
     * - 检查完全平方数：O(√n)
     * - 检查4^k(8m+7)形式：O(log n)
     * - 总时间复杂度：O(√n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param n 目标整数
     * @return 和为n的完全平方数的最少数量
     */
    public int numSquaresMath(int n) {
        // 情况1：n本身就是完全平方数
        if (isPerfectSquare(n)) {
            return 1;
        }

        // 情况2：可以表示为两个完全平方数之和
        for (int i = 1; i * i <= n; i++) {
            if (isPerfectSquare(n - i * i)) {
                return 2;
            }
        }

        // 情况3：检查是否为4^k(8m+7)的形式
        // 如果是，则需要4个完全平方数
        int temp = n;
        while (temp % 4 == 0) {
            temp /= 4;
        }
        if (temp % 8 == 7) {
            return 4;
        }

        // 其他情况需要3个完全平方数
        return 3;
    }

    /**
     * 辅助方法：判断是否为完全平方数
     *
     * 时间复杂度分析：
     * - 计算平方根：O(1)
     * - 验证平方：O(1)
     * - 总时间复杂度：O(1)
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param n 待判断的数
     * @return 如果是完全平方数返回true，否则返回false
     */
    private boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
