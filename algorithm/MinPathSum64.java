package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 最小路径和（LeetCode 64）- 动态规划
 *
 * 时间复杂度：O(m * n)
 * - m是网格的行数，n是网格的列数
 * - 需要遍历整个网格一次
 *
 * 空间复杂度：O(m * n)
 * - 需要m*n的DP表存储中间结果
 * - 可以优化到O(n)或O(1)（如果允许修改原网格）
 */
public class MinPathSum64 {

    /**
     * 计算从左上角到右下角的最小路径和
     *
     * 算法思路：
     * 使用动态规划，定义`dp[i][j]`表示从左上角到位置(i,j)的最小路径和
     * 状态转移方程：
     * `dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])`
     *
     * 边界条件：
     * 1. 第一行：`dp[0][j] = dp[0][j-1] + grid[0][j]`
     * 2. 第一列：`dp[i][0] = dp[i-1][0] + grid[i][0]`
     *
     * 执行过程分析（以`grid=[[1,3,1],[1,5,1],[4,2,1]]`为例）：
     *
     * 原始网格：
     * 1 3 1
     * 1 5 1
     * 4 2 1
     *
     * 填充DP表：
     * 初始状态：
     * 1 3 1
     * 1 5 1
     * 4 2 1
     *
     * 处理第一行：
     * 1 4 5
     * 1 5 1
     * 4 2 1
     *
     * 处理第一列：
     * 1 4 5
     * 2 5 1
     * 6 2 1
     *
     * 填充其余位置：
     * `dp[1][1] = 5 + min(4,2) = 7`
     * `dp[1][2] = 1 + min(5,7) = 6`
     * `dp[2][1] = 2 + min(6,7) = 8`
     * `dp[2][2] = 1 + min(6,8) = 7`
     *
     * 最终DP表：
     * 1 4 5
     * 2 7 6
     * 6 8 7
     *
     * 最小路径和：`dp[2][2] = 7`
     * 对应路径：1→1→1→1→1 = 7（路径可以是右→右→下→下→下或右→下→下→右→下等）
     *
     * 时间复杂度分析：
     * - 初始化边界：O(m + n)
     * - 填充DP表：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(m * n)
     *
     * @param grid 包含非负整数的m×n网格
     * @return 从左上角到右下角的最小路径和
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;    // 行数
        int n = grid[0].length; // 列数

        // 创建DP表，`dp[i][j]`表示从左上角到位置(i,j)的最小路径和
        int[][] dp = new int[m][n];

        // 初始化起点
        dp[0][0] = grid[0][0];

        // 初始化第一行（只能从左边来）
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 初始化第一列（只能从上面来）
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // 填充DP表的其余位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前位置的最小路径和 = 当前值 + min(从上面来, 从左边来)
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // 返回右下角的最小路径和
        return dp[m - 1][n - 1];
    }

    /**
     * 空间优化版本：直接修改原网格
     *
     * 算法思路：
     * 直接在原网格上进行动态规划计算，节省额外空间
     *
     * 时间复杂度分析：
     * - 初始化边界：O(m + n)
     * - 填充网格：O(m * n)
     * - 总时间复杂度：O(m * n)
     *
     * 空间复杂度分析：
     * - 直接使用原网格：O(1)额外空间
     *
     * @param grid 包含非负整数的m×n网格
     * @return 从左上角到右下角的最小路径和
     */
    public int minPathSumOptimized(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // 初始化第一行
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }

        // 初始化第一列
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        // 填充其余位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[m - 1][n - 1];
    }

    /**
     * 辅助方法：读取用户输入的网格
     *
     * 时间复杂度分析：
     * - 读取输入：O(m * n)
     *
     * 空间复杂度分析：
     * - 存储网格：O(m * n)
     *
     * @return 用户输入的二维整数数组
     */
    public static int[][] readGrid() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入网格的行数: ");
        int m = scanner.nextInt();
        System.out.print("请输入网格的列数: ");
        int n = scanner.nextInt();

        int[][] grid = new int[m][n];
        System.out.println("请输入网格元素（按行输入，每行元素用空格分隔）：");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        return grid;
    }

    /**
     * 辅助方法：打印网格
     *
     * 时间复杂度分析：
     * - 打印网格：O(m * n)
     *
     * 空间复杂度分析：
     * - 无额外空间使用：O(1)
     *
     * @param grid 二维整数数组
     */
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * 主函数：处理用户输入并计算最小路径和
     */
    public static void main(String[] args) {
        System.out.println("最小路径和计算");

        // 读取用户输入的网格
        int[][] grid = readGrid();

        System.out.println("输入的网格:");
        printGrid(grid);

        // 计算最小路径和
        MinPathSum64 solution = new MinPathSum64();
        int result1 = solution.minPathSum(grid);

        // 为了演示优化版本，需要重新创建网格（因为原网格被修改了）
        int[][] gridCopy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, gridCopy[i], 0, grid[i].length);
        }

        int result2 = solution.minPathSumOptimized(gridCopy);

        // 输出结果
        System.out.println("标准动态规划方法最小路径和: " + result1);
        System.out.println("空间优化方法最小路径和: " + result2);
    }
}
