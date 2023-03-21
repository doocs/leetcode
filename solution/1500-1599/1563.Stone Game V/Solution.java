class Solution {
    private int n;
    private int[] s;
    private int[] stoneValue;
    private Integer[][] f;

    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        this.stoneValue = stoneValue;
        s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        f = new Integer[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        int a = 0;
        for (int k = i; k < j; ++k) {
            a += stoneValue[k];
            int b = s[j + 1] - s[i] - a;
            if (a < b) {
                if (ans >= a * 2) {
                    continue;
                }
                ans = Math.max(ans, a + dfs(i, k));
            } else if (a > b) {
                if (ans >= b * 2) {
                    break;
                }
                ans = Math.max(ans, b + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, Math.max(a + dfs(i, k), b + dfs(k + 1, j)));
            }
        }
        return f[i][j] = ans;
    }
}