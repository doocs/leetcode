class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[] {heightMap[i][j], i, j});
                    vis[i][j] = true;
                }
            }
        }
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            var p = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[1] + dirs[k], y = p[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                    ans += Math.max(0, p[0] - heightMap[x][y]);
                    vis[x][y] = true;
                    pq.offer(new int[] {Math.max(p[0], heightMap[x][y]), x, y});
                }
            }
        }
        return ans;
    }
}