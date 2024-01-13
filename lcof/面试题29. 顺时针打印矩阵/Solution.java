class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] vis = new boolean[m][n];
        int[] ans = new int[m * n];
        int i = 0, j = 0, k = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        for (int h = 0; h < m * n; ++h) {
            ans[h] = matrix[i][j];
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k];
                y = j + dirs[k + 1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
}