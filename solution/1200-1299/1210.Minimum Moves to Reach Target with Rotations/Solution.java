class Solution {
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[] target = new int[]{n * n - 2, n * n - 1};
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1});
        boolean[][] vis = new boolean[n * n][n * n];
        int ans = 0;
        vis[0][1] = true;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == target[0] && p[1] == target[1]) {
                    return ans;
                }
                int a = p[0], b = p[1];
                int i1 = a / n, j1 = a % n;
                int i2 = b / n, j2 = b % n;
                if (j1 + 1 < n && j2 + 1 < n && grid[i1][j1 + 1] == 0 && grid[i2][j2 + 1] == 0) {
                    check(i1 * n + j1 + 1, i2 * n + j2 + 1, q, vis);
                    if (j1 == j2) {
                        check(a, i1 * n + j2 + 1, q, vis);
                    }
                }
                if (i1 + 1 < n && i2 + 1 < n && grid[i1 + 1][j1] == 0 && grid[i2 + 1][j2] == 0) {
                    check((i1 + 1) * n + j1, (i2 + 1) * n + j2, q, vis);
                    if (i1 == i2) {
                        check(a, (i2 + 1) * n + j1, q, vis);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    private void check(int a, int b, Deque<int[]> q, boolean[][] vis) {
        if (!vis[a][b]) {
            vis[a][b] = true;
            q.offer(new int[]{a, b});
        }
    }
}