class Solution {
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length, n = grid[0].length;
        int row = start[0], col = start[1];
        int low = pricing[0], high = pricing[1];
        List<int[]> items = new ArrayList<>();
        if (low <= grid[row][col] && grid[row][col] <= high) {
            items.add(new int[]{0, grid[row][col], row, col});
        }
        grid[row][col] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col, 0});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1], d = p[2];
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                    if (low <= grid[x][y] && grid[x][y] <= high) {
                        items.add(new int[]{d + 1, grid[x][y], x, y});
                    }
                    grid[x][y] = 0;
                    q.offer(new int[]{x, y, d + 1});
                }
            }
        }
        items.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            if (a[2] != b[2]) {
                return a[2] - b[2];
            }
            return a[3] - b[3];
        });
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < items.size() && i < k; ++i) {
            int[] p = items.get(i);
            ans.add(Arrays.asList(p[2], p[3]));
        }
        return ans;
    }
}