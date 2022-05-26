class Solution {
    private int[][] pre;
    private int m;
    private int n;
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + - pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        this.pre = pre;
        this.m = m;
        this.n = n;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1) - get(i + k + 1, j - k) - get(i - k, j + k + 1) + get(i - k, j - k);
            }
        }
        return ans;
    }

    private int get(int i, int j) {
        i = Math.max(Math.min(m, i), 0);
        j = Math.max(Math.min(n, j), 0);
        return pre[i][j];
    }
}