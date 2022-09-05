class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(ans[i], -1);
        }
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            for (int i = 0; i < 4; ++i) {
                int x = t[0] + dirs[i];
                int y = t[1] + dirs[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[t[0]][t[1]] + 1;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}