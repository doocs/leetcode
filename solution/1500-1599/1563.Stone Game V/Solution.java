class Solution {
    private int n;
    private int[] s;
    private int[] nums;
    private Integer[][] f;

    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        s = new int[n + 1];
        nums = stoneValue;
        f = new Integer[n][n];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0, l = 0, r = s[j + 1] - s[i];
        for (int k = i; k < j; ++k) {
            l += nums[k];
            r -= nums[k];
            if (l < r) {
                if (ans > l * 2) {
                    continue;
                }
                ans = Math.max(ans, l + dfs(i, k));
            } else if (l > r) {
                if (ans > r * 2) {
                    break;
                }
                ans = Math.max(ans, r + dfs(k + 1, j));
            } else {
                ans = Math.max(ans, Math.max(l + dfs(i, k), r + dfs(k + 1, j)));
            }
        }
        return f[i][j] = ans;
    }
}
