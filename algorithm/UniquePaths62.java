package com.funian.algorithm.algorithm;

/**
 * 不同路径（LeetCode 62）- 动态规划
 *
 * 时间复杂度：O(m * n)
 * - m是网格的行数，n是网格的列数
 * - 需要填充m*n的DP表
 *
 * 空间复杂度：O(m * n)
 * - 需要m*n的DP表存储中间结果
 * - 可以优化到O(n)或O(1)（使用数学公式）
 */
import java.util.Scanner;

public class UniquePaths62 {

    /**
     * 主函数：处理用户输入并计算不同路径的数量
     *
     * 算法流程：
     * 1. 读取用户输入的网格行数和列数
     * 2. 调用 [uniquePaths](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/UniquePaths62.java#L109-L144)方法计算不同路径的数量
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入网格的行数和列数
        System.out.print("请输入网格的行数 m: ");
        int m = scanner.nextInt();
        System.out.print("请输入网格的列数 n: ");
        int n = scanner.nextInt();

        // 调用 uniquePaths 方法计算不同路径的数量
        int result = uniquePaths(m, n);
        // 输出结果
        System.out.println("从左上角到右下角的不同路径数量：" + result);

        // 演示其他解法
        UniquePaths62 solution = new UniquePaths62();
        int result2 = solution.uniquePathsOptimized(m, n);
        int result3 = solution.uniquePathsMath(m, n);
        System.out.println("空间优化版本结果：" + result2);
        System.out.println("数学公式版本结果：" + result3);
    }

    /**
     * 计算从网格左上角到右下角的不同路径数量
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i][j]`表示从起点(0,0)到位置(i,j)的不同路径数量
     * 状态转移方程：
     * `dp[i][j] = dp[i-1][j] + dp[i][j-1]`
     * （当前位置的路径数 = 从上方来的路径数 + 从左方来的路径数）
     *
     * 边界条件：
     * 1. 第一行：`dp[0][j] = 1`（只能一直向右）
     * 2. 第一列：`dp[i][0] = 1`（只能一直向下）
     *
     * 执行过程分析（以m=3, n=3为例）：
     *
     * 初始化DP表：
     * 1 1 1
     * 1 0 0
     * 1 0 0
     *
     * 填充DP表：
     * `dp[1][1] = dp[0][1] + dp[1][0] = 1 + 1 = 2`
     * `dp[1][2] = dp[0][2] + dp[1][1] = 1 + 2 = 3`
     * `dp[2][1] = dp[1][1] + dp[2][0] = 2 + 1 = 3`
     * `dp[2][2] = dp[1][2] + dp[2][1] = 3 + 3 = 6`
     *
     * 最终DP表：
     * 1 1 1
     * 1 2 3
     * 1 3 6
     *
     * 结果：`dp[2][2] = 6`
     *
     * 路径示意图：
     * 右→右→下→下
     * 右→下→右→下
     * 右→下→下→右
     * 下→右→右→下
     * 下→右→下→右
     * 下→下→右→右
     *
     * 时间复杂度分析：
     * - 初始化边界：O(m + n)
     * - 填充DP表：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(m * n)
     *
     * @param m 网格行数
     * @param n 网格列数
     * @return 不同路径的数量
     */
    public static int uniquePaths(int m, int n) {
        // 创建一个二维数组 dp，大小为 m x n
        // dp[i][j] 表示从起点到位置(i,j)的不同路径数量
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1; // 第一列只有一种路径（一直向下）
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1; // 第一行只有一种路径（一直向右）
        }

        // 填充 dp 数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前路径数量等于上方和左方路径数量之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回右下角的路径数量
        return dp[m - 1][n - 1];
    }

    /**
     * 空间优化版本：只使用一维数组
     *
     * 算法思路：
     * 观察DP表的填充过程，发现每次只需要前一行的信息
     * 可以用一维数组代替二维数组
     *
     * 时间复杂度分析：
     * - 初始化数组：O(n)
     * - 填充数组：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - 一维数组存储空间：O(n)
     *
     * @param m 网格行数
     * @param n 网格列数
     * @return 不同路径的数量
     */
    public int uniquePathsOptimized(int m, int n) {
        // 只需要一维数组，长度为列数
        int[] dp = new int[n];

        // 初始化数组为1（第一行的值）
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        // 填充数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] = dp[j] (上方的值) + dp[j-1] (左方的值)
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }

    /**
     * 数学公式版本：组合数学解法
     *
     * 算法思路：
     * 从(0,0)到(m-1,n-1)总共需要走(m-1)+(n-1) = m+n-2步
     * 其中需要向右走(n-1)步，向下走(m-1)步
     * 问题转化为：在m+n-2步中选择n-1步向右走的方案数
     * 即组合数C(m+n-2, n-1)
     *
     * 时间复杂度分析：
     * - 计算组合数：O(min(m,n))
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param m 网格行数
     * @param n 网格列数
     * @return 不同路径的数量
     */
    public int uniquePathsMath(int m, int n) {
        // 计算组合数C(m+n-2, min(m-1,n-1))
        // 为了避免计算大数阶乘，我们计算C(m+n-2, min(m-1,n-1))
        int totalSteps = m + n - 2;
        int rightSteps = Math.min(m - 1, n - 1);

        long result = 1;
        for (int i = 1; i <= rightSteps; i++) {
            // 逐步计算组合数
            result = result * (totalSteps - rightSteps + i) / i;
        }

        return (int) result;
    }
}
