class Solution {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        PriorityQueue<int[]>[] row = new PriorityQueue[m];
        PriorityQueue<int[]>[] col = new PriorityQueue[n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], -1);
            row[i] = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        }
        for (int i = 0; i < n; ++i) {
            col[i] = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        }
        dist[0][0] = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                while (!row[i].isEmpty() && grid[i][row[i].peek()[1]] + row[i].peek()[1] < j) {
                    row[i].poll();
                }
                if (!row[i].isEmpty() && (dist[i][j] == -1 || row[i].peek()[0] + 1 < dist[i][j])) {
                    dist[i][j] = row[i].peek()[0] + 1;
                }
                while (!col[j].isEmpty() && grid[col[j].peek()[1]][j] + col[j].peek()[1] < i) {
                    col[j].poll();
                }
                if (!col[j].isEmpty() && (dist[i][j] == -1 || col[j].peek()[0] + 1 < dist[i][j])) {
                    dist[i][j] = col[j].peek()[0] + 1;
                }
                if (dist[i][j] != -1) {
                    row[i].offer(new int[]{dist[i][j], j});
                    col[j].offer(new int[]{dist[i][j], i});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}