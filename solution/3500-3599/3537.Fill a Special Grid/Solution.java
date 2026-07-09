class Solution {
    private int[][] ans;
    private int val;

    public int[][] specialGrid(int n) {
        int m = 1 << n;
        ans = new int[m][m];
        dfs(0, m - 1, m);
        return ans;
    }

    private void dfs(int x, int y, int k) {
        if (k == 1) {
            ans[x][y] = val++;
            return;
        }

        int h = k / 2;
        dfs(x, y, h);
        dfs(x + h, y, h);
        dfs(x + h, y - h, h);
        dfs(x, y - h, h);
    }
}