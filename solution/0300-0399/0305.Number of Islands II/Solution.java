class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private int[][] grid;
    private int m;
    private int n;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        grid = new int[m][n];
        this.m = m;
        this.n = n;
        List<Integer> res = new ArrayList<>();
        int cur = 0;
        for (int[] position : positions) {
            int i = position[0], j = position[1];
            if (grid[i][j] == 1) {
                res.add(cur);
                continue;
            }
            grid[i][j] = 1;
            ++cur;
            for (int[] e : dirs) {
                if (check(i + e[0], j + e[1]) && find(i * n + j) != find((i + e[0]) * n + j + e[1])) {
                    p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                    --cur;
                }
            }
            res.add(cur);
        }
        return res;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}