class Solution {
    private int[] p;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[][] grid = new int[m][n];
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            if (grid[i][j] == 1) {
                ans.add(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1
                    && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.add(cnt);
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