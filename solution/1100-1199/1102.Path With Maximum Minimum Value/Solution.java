class Solution {
    private int[] p;

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int ans = Math.min(grid[0][0], grid[m - 1][n - 1]);
        Set<Integer> vis = new HashSet<>(Arrays.asList(0, m * n - 1));
        List<int[]> scores = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                scores.add(new int[]{grid[i][j], i, j});
            }
        }
        scores.sort(Comparator.comparingInt(a -> a[0]));
        int[] dirs = {-1, 0, 1, 0, -1};
        while (find(0) != find(m * n - 1)) {
            int[] t = scores.remove(scores.size() - 1);
            int score = t[0], i = t[1], j = t[2];
            ans = Math.min(ans, score);
            vis.add(i * n + j);
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis.contains(x * n + y)) {
                    p[find(x * n + y)] = find(i * n + j);
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}