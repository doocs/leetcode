class Solution {
    private Integer[][] f;

    public int countNumbersWithUniqueDigits(int n) {
        f = new Integer[n][1 << 10];
        return dfs(n - 1, 0, true);
    }

    private int dfs(int i, int mask, boolean lead) {
        if (i < 0) {
            return 1;
        }
        if (!lead && f[i][mask] != null) {
            return f[i][mask];
        }
        int ans = 0;
        for (int j = 0; j <= 9; ++j) {
            if ((mask >> j & 1) == 1) {
                continue;
            }
            if (lead && j == 0) {
                ans += dfs(i - 1, mask, true);
            } else {
                ans += dfs(i - 1, mask | 1 << j, false);
            }
        }
        if (!lead) {
            f[i][mask] = ans;
        }
        return ans;
    }
}
