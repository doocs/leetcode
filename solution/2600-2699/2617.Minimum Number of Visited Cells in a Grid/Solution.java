class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[1], j = p[2], dist = p[0];
            if (i == m - 1 && j == n - 1) {
                return dist;
            }
            int k = Math.max(row[i], j) + 1;
            for (; k < Math.min(n, j + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, i, k});
                row[i] = k;
            }
            k = Math.max(col[j], i) + 1;
            for (; k < Math.min(m, i + grid[i][j] + 1); ++k) {
                q.offer(new int[] {dist + 1, k, j});
                col[j] = k;
            }
        }
        return -1;
    }
}