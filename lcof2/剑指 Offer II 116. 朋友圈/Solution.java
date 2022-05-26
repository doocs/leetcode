class Solution {
    private int[][] isConnected;
    private boolean[] vis;
    private int n;

    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        vis = new boolean[n];
        this.isConnected = isConnected;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                dfs(i);
                ++ans;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        vis[i] = true;
        for (int j = 0; j < n; ++j) {
            if (!vis[j] && isConnected[i][j] == 1) {
                dfs(j);
            }
        }
    }
}