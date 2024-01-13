public class Solution {
    public int MaxProfit(int k, int[] prices) {
        int n = prices.Length;
        int[,] f = new int[k + 1, 2];
        for (int j = 1; j <= k; ++j) {
            f[j, 1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j, 0] = Math.Max(f[j, 1] + prices[i], f[j, 0]);
                f[j, 1] = Math.Max(f[j - 1, 0] - prices[i], f[j, 1]);
            }
        }
        return f[k, 0];
    }
}
