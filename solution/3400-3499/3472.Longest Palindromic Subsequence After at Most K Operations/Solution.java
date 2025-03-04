class Solution {
    private char[] s;
    private Integer[][][] f;

    public int longestPalindromicSubsequence(String s, int k) {
        this.s = s.toCharArray();
        int n = s.length();
        f = new Integer[n][n][k + 1];
        return dfs(0, n - 1, k);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        int d = Math.abs(s[i] - s[j]);
        int t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        f[i][j][k] = res;
        return res;
    }
}
