class Solution {
    private boolean[][] vis;
    private int m;
    private int n;
    private int k;
    private int ans;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        vis = new boolean[m][n];
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int j) {
        vis[i][j] = true;
        ++ans;
        int[] dirs = {1, 0, 1};
        for (int l = 0; l < 2; ++l) {
            int x = i + dirs[l], y = j + dirs[l + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && f(x) + f(y) <= k && !vis[x][y]) {
                dfs(x, y);
            }
        }
    }

    private int f(int x) {
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s;
    }
}