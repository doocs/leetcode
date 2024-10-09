class Solution {
    private Long[][] f;
    private int[] a;
    private int[] b;

    public long maxScore(int[] a, int[] b) {
        f = new Long[a.length][b.length];
        this.a = a;
        this.b = b;
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (j >= b.length) {
            return i >= a.length ? 0 : Long.MIN_VALUE / 2;
        }
        if (i >= a.length) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        return f[i][j] = Math.max(dfs(i, j + 1), 1L * a[i] * b[j] + dfs(i + 1, j + 1));
    }
}
