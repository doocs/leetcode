class Solution {
    private int[][][] f;
    private int[] b;

    public int removeBoxes(int[] boxes) {
        b = boxes;
        int n = b.length;
        f = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        while (i < j && b[j] == b[j - 1]) {
            --j;
            ++k;
        }
        if (f[i][j][k] > 0) {
            return f[i][j][k];
        }
        int ans = dfs(i, j - 1, 0) + (k + 1) * (k + 1);
        for (int h = i; h < j; ++h) {
            if (b[h] == b[j]) {
                ans = Math.max(ans, dfs(h + 1, j - 1, 0) + dfs(i, h, k + 1));
            }
        }
        f[i][j][k] = ans;
        return ans;
    }
}