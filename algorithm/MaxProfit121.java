package com.funian.algorithm.algorithm;

/**
 * 买卖股票的最佳时机（LeetCode 121）
 *
 * 时间复杂度：O(n)
 * - n是价格数组的长度
 * - 只需要遍历数组一次
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
import java.util.Scanner;
import java.util.Arrays;

public class MaxProfit121 {

    /**
     * 主函数：处理用户输入并计算最大利润
     *
     * 算法流程：
     * 1. 读取用户输入的股票价格数组
     * 2. 调用 [maxProfit](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/MaxProfit121.java#L97-L124)方法计算最大利润
     * 3. 输出结果
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入股票价格（以空格分隔）：");
        String line = scanner.nextLine();
        String[] strPrices = line.split(" ");
        int n = strPrices.length;
        int[] prices = new int[n];

        // 将输入的字符串价格转换为整数数组
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(strPrices[i]);
        }

        int maxProfit = maxProfit(prices);
        System.out.println("最大利润为：" + maxProfit);
    }

    /**
     * 计算买卖股票的最大利润
     *
     * 算法思路：
     * 一次遍历，维护两个变量：
     * 1. minPrice：到目前为止遇到的最低价格（最佳买入时机）
     * 2. maxProfit：到目前为止能获得的最大利润
     *
     * 对于每一天的价格，我们：
     * 1. 更新历史最低价格
     * 2. 计算如果今天卖出能获得的利润
     * 3. 更新最大利润
     *
     * 执行过程分析（以prices=[7,1,5,3,6,4]为例）：
     *
     * 初始状态：
     * minPrice = MAX_VALUE
     * maxProfit = 0
     *
     * 遍历过程：
     * day 0, price=7:
     *   更新minPrice = 7
     *   profit = 7-7 = 0
     *   maxProfit = max(0, 0) = 0
     *
     * day 1, price=1:
     *   更新minPrice = 1 (1 < 7)
     *   profit = 1-1 = 0
     *   maxProfit = max(0, 0) = 0
     *
     * day 2, price=5:
     *   minPrice保持1 (5 > 1)
     *   profit = 5-1 = 4
     *   maxProfit = max(0, 4) = 4
     *
     * day 3, price=3:
     *   minPrice保持1 (3 > 1)
     *   profit = 3-1 = 2
     *   maxProfit = max(4, 2) = 4
     *
     * day 4, price=6:
     *   minPrice保持1 (6 > 1)
     *   profit = 6-1 = 5
     *   maxProfit = max(4, 5) = 5
     *
     * day 5, price=4:
     *   minPrice保持1 (4 > 1)
     *   profit = 4-1 = 3
     *   maxProfit = max(5, 3) = 5
     *
     * 最终结果：maxProfit = 5
     * 最佳策略：第2天买入(price=1)，第5天卖出(price=6)，利润=6-1=5
     *
     * 时间复杂度分析：
     * - 遍历价格数组：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param prices 股票价格数组，prices[i]表示第i天的股票价格
     * @return 能获得的最大利润
     */
    public static int maxProfit(int[] prices) {
        // 初始化最小价格为最大整数值
        // 这样确保第一个价格一定会更新minPrice
        int minPrice = Integer.MAX_VALUE;

        // 初始化最大利润为0
        // 如果无法获得正利润，就返回0（不交易）
        int maxProfit = 0;

        // 遍历每一天的价格
        for (int price : prices) {
            // 更新到目前为止的最小价格（最佳买入时机）
            if (price < minPrice) {
                minPrice = price;
            }

            // 计算如果今天卖出能获得的利润
            int profit = price - minPrice;

            // 更新到目前为止的最大利润
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        // 返回最大利润
        return maxProfit;
    }

    /**
     * 方法2：动态规划解法
     *
     * 算法思路：
     * 定义两个状态：
     * 1. hold：持有股票时的最大收益
     * 2. sold：不持有股票时的最大收益
     *
     * 状态转移：
     * hold = max(hold, -price) // 继续持有 或 买入
     * sold = max(sold, hold + price) // 继续不持有 或 卖出
     *
     * 时间复杂度分析：
     * - 遍历价格数组：O(n)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param prices 股票价格数组
     * @return 能获得的最大利润
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int hold = -prices[0]; // 持有股票
        int sold = 0;          // 不持有股票

        for (int i = 1; i < prices.length; i++) {
            sold = Math.max(sold, hold + prices[i]); // 卖出股票
            hold = Math.max(hold, -prices[i]);       // 买入股票
        }

        return sold;
    }

    /**
     * 方法3：暴力解法（仅供学习，效率较低）
     *
     * 算法思路：
     * 尝试所有可能的买入和卖出组合
     *
     * 时间复杂度分析：
     * - 双重循环：O(n²)
     * - 每次操作：O(1)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param prices 股票价格数组
     * @return 能获得的最大利润
     */
    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }
}
