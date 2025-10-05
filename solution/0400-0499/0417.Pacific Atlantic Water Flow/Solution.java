class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] vis1 = new boolean[m][n];
        boolean[][] vis2 = new boolean[m][n];
        Deque<int[]> q1 = new ArrayDeque<>();
        Deque<int[]> q2 = new ArrayDeque<>();
        int[] dirs = {-1, 0, 1, 0, -1};

        for (int i = 0; i < m; ++i) {
            q1.offer(new int[] {i, 0});
            vis1[i][0] = true;
            q2.offer(new int[] {i, n - 1});
            vis2[i][n - 1] = true;
        }
        for (int j = 0; j < n; ++j) {
            q1.offer(new int[] {0, j});
            vis1[0][j] = true;
            q2.offer(new int[] {m - 1, j});
            vis2[m - 1][j] = true;
        }

        BiConsumer<Deque<int[]>, boolean[][]> bfs = (q, vis) -> {
            while (!q.isEmpty()) {
                var cell = q.poll();
                int x = cell[0], y = cell[1];
                for (int k = 0; k < 4; ++k) {
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !vis[nx][ny]
                        && heights[nx][ny] >= heights[x][y]) {
                        vis[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        };

        bfs.accept(q1, vis1);
        bfs.accept(q2, vis2);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (vis1[i][j] && vis2[i][j]) {
                    ans.add(List.of(i, j));
                }
            }
        }
        return ans;
    }
}
