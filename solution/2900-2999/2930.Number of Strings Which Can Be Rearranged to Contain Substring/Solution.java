class Solution {
    private final int mod = (int) 1e9 + 7;
    private Long[][][][] f;

    public int stringCount(int n) {
        f = new Long[n + 1][2][3][2];
        return (int) dfs(n, 0, 0, 0);
    }

    private long dfs(int i, int l, int e, int t) {
        if (i == 0) {
            return l == 1 && e == 2 && t == 1 ? 1 : 0;
        }
        if (f[i][l][e][t] != null) {
            return f[i][l][e][t];
        }
        long a = dfs(i - 1, l, e, t) * 23 % mod;
        long b = dfs(i - 1, Math.min(1, l + 1), e, t);
        long c = dfs(i - 1, l, Math.min(2, e + 1), t);
        long d = dfs(i - 1, l, e, Math.min(1, t + 1));
        return f[i][l][e][t] = (a + b + c + d) % mod;
    }
}