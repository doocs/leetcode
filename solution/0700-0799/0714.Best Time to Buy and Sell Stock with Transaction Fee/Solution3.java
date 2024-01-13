class Solution {
    public int maxProfit(int[] prices, int fee) {
        int f0 = 0, f1 = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            int g0 = Math.max(f0, f1 + prices[i] - fee);
            f1 = Math.max(f1, f0 - prices[i]);
            f0 = g0;
        }
        return f0;
    }
}