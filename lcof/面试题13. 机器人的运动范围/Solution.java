class Solution {
    private int m;
    private int n;
    private boolean[][] visited;
    private int cnt;
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        this.m = m;
        this.n = n;
        cnt = 0;
        dfs(0, 0, k);
        return cnt;
    }

    private void dfs(int i, int j, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || cal(i, j) > k) return;
        ++cnt;
        visited[i][j] = true;
        dfs(i + 1, j, k);
        dfs(i - 1, j, k);
        dfs(i, j + 1, k);
        dfs(i, j - 1, k);
    }

    private int cal(int i, int j) {
        int res = 0;
        while (i != 0) {
            res += (i % 10);
            i /= 10;
        }
        while (j != 0) {
            res += (j % 10);
            j /= 10;
        }
        return res;
    }
}