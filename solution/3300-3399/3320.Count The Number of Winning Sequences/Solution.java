class Solution {
    private int n;
    private char[] s;
    private int[] d = new int[26];
    private Integer[][][] f;
    private final int mod = (int) 1e9 + 7;

    public int countWinningSequences(String s) {
        d['W' - 'A'] = 1;
        d['E' - 'A'] = 2;
        this.s = s.toCharArray();
        n = this.s.length;
        f = new Integer[n][n + n + 1][4];
        return dfs(0, n, 3);
    }

    private int dfs(int i, int j, int k) {
        if (n - i <= j - n) {
            return 0;
        }
        if (i >= n) {
            return j - n < 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }

        int ans = 0;
        for (int l = 0; l < 3; ++l) {
            if (l == k) {
                continue;
            }
            ans = (ans + dfs(i + 1, j + calc(d[s[i] - 'A'], l), l)) % mod;
        }
        return f[i][j][k] = ans;
    }

    private int calc(int x, int y) {
        if (x == y) {
            return 0;
        }
        if (x < y) {
            return x == 0 && y == 2 ? 1 : -1;
        }
        return x == 2 && y == 0 ? -1 : 1;
    }
}
