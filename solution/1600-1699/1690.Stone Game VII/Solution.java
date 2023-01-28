class Solution {
    private int[] s;
    private Integer[][] f;

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        s = new int[n + 1];
        f = new Integer[n][n];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
        int b = s[j] - s[i] - dfs(i, j - 1);
        return f[i][j] = Math.max(a, b);
    }
}