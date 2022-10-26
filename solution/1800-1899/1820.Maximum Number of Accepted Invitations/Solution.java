class Solution {
    private int[][] grid;
    private boolean[] vis;
    private int[] match;
    private int n;

    public int maximumInvitations(int[][] grid) {
        int m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        vis = new boolean[n];
        match = new int[n];
        Arrays.fill(match, -1);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(vis, false);
            if (find(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean find(int i) {
        for (int j = 0; j < n; ++j) {
            if (grid[i][j] == 1 && !vis[j]) {
                vis[j] = true;
                if (match[j] == -1 || find(match[j])) {
                    match[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
}