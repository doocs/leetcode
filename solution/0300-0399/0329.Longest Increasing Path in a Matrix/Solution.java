class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] outdegree = new int[m][n];
        int[][] length = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                length[i][j] = 1;
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k];
                    int y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        ++outdegree[i][j];
                    }
                }
                if (outdegree[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }

        int ans = 1;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            ans = Math.max(ans, length[i][j]);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                    length[x][y] = Math.max(length[x][y], length[i][j] + 1);
                    if (--outdegree[x][y] == 0) {
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
        return ans;
    }
}
