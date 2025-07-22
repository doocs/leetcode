class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        final int[] dirs = {0, 1, 0, -1, 0};
        int i = 0, j = 0, k = 0;
        for (int v = 1; v <= n * n; ++v) {
            ans[i][j] = v;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] != 0) {
                k = (k + 1) % 4;
            }
            i += dirs[k];
            j += dirs[k + 1];
        }
        return ans;
    }
}
