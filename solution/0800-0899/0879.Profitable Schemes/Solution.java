class Solution {
    private Integer[][][] f;
    private int m;
    private int n;
    private int minProfit;
    private int[] group;
    private int[] profit;
    private final int mod = (int) 1e9 + 7;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        m = group.length;
        this.n = n;
        f = new Integer[m][n + 1][minProfit + 1];
        this.minProfit = minProfit;
        this.group = group;
        this.profit = profit;
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= m) {
            return k == minProfit ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, k);
        if (j + group[i] <= n) {
            ans += dfs(i + 1, j + group[i], Math.min(k + profit[i], minProfit));
        }
        ans %= mod;
        return f[i][j][k] = ans;
    }
}