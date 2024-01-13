public class Solution {
    private int[,,] f;
    private int[] prices;
    private int n;

    public int MaxProfit(int k, int[] prices) {
        n = prices.Length;
        f = new int[n, k + 1, 2];
        this.prices = prices;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i, j, 0] = -1;
                f[i, j, 1] = -1;
            }
        }
        return dfs(0, k, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i, j, k] != -1) {
            return f[i, j, k];
        }
        int ans = dfs(i + 1, j, k);
        if (k > 0) {
            ans = Math.Max(ans, prices[i] + dfs(i + 1, j, 0));
        }
        else if (j > 0) {
            ans = Math.Max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        return f[i, j, k] = ans;
    }
}
