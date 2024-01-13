class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] f = new int[] {-prices[0], 0};
        for (int i = 1; i < n; ++i) {
            int[] g = new int[2];
            g[0] = Math.max(f[0], f[1] - prices[i]);
            g[1] = Math.max(f[1], f[0] + prices[i]);
            f = g;
        }
        return f[1];
    }
}