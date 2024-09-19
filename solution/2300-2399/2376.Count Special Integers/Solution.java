class Solution {
    private char[] s;
    private Integer[][] f;

    public int countSpecialNumbers(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][1 << 10];
        return dfs(0, 0, true, true);
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] != null) {
            return f[i][mask];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if ((mask >> j & 1) == 1) {
                continue;
            }
            if (lead && j == 0) {
                ans += dfs(i + 1, mask, true, limit && j == up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
            }
        }
        if (!limit && !lead) {
            f[i][mask] = ans;
        }
        return ans;
    }
}
