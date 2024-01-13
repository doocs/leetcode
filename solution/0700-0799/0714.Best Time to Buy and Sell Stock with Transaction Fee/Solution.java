class Solution {
    private Integer[][] f;
    private int[] prices;
    private int fee;

    public int maxProfit(int[] prices, int fee) {
        f = new Integer[prices.length][2];
        this.prices = prices;
        this.fee = fee;
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= prices.length) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = dfs(i + 1, j);
        if (j > 0) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, 0) - fee);
        } else {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, 1));
        }
        return f[i][j] = ans;
    }
}