class Solution {
    private int[][][] f;
    private int[] price;
    private int[] tastiness;
    private int n;

    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        n = price.length;
        this.price = price;
        this.tastiness = tastiness;
        f = new int[n][maxAmount + 1][maxCoupons + 1];
        return dfs(0, maxAmount, maxCoupons);
    }

    private int dfs(int i, int j, int k) {
        if (i == n) {
            return 0;
        }
        if (f[i][j][k] != 0) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, k);
        if (j >= price[i]) {
            ans = Math.max(ans, dfs(i + 1, j - price[i], k) + tastiness[i]);
        }
        if (j >= price[i] / 2 && k > 0) {
            ans = Math.max(ans, dfs(i + 1, j - price[i] / 2, k - 1) + tastiness[i]);
        }
        f[i][j][k] = ans;
        return ans;
    }
}