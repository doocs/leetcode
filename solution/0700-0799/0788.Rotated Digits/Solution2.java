class Solution {
    private char[] s;
    private Integer[][] f;

    public int rotatedDigits(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][2];
        return dfs(0, 0, true);
    }

    private int dfs(int i, int ok, boolean limit) {
        if (i >= s.length) {
            return ok;
        }
        if (!limit && f[i][ok] != null) {
            return f[i][ok];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 0 || j == 1 || j == 8) {
                ans += dfs(i + 1, ok, limit && j == up);
            } else if (j == 2 || j == 5 || j == 6 || j == 9) {
                ans += dfs(i + 1, 1, limit && j == up);
            }
        }
        if (!limit) {
            f[i][ok] = ans;
        }
        return ans;
    }
}
