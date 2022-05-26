class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{heightMap[i][j], i, j});
                    vis[i][j] = true;
                }
            }
        }
        int ans = 0;
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        while (!pq.isEmpty()) {
            int[] e = pq.poll();
            for (int[] d : dirs) {
                int i = e[1] + d[0], j = e[2] + d[1];
                if (i >= 0 && i < m && j >= 0 && j < n && !vis[i][j]) {
                    if (heightMap[i][j] < e[0]) {
                        ans += e[0] - heightMap[i][j];
                    }
                    vis[i][j] = true;
                    pq.offer(new int[]{Math.max(heightMap[i][j], e[0]), i, j});
                }
            }
        }
        return ans;
    }
}