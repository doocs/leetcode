class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int mx;
    private int[][] grid;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        mx = 1;
        this.grid = grid;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            union(i * n + j, (i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        int res = mx;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> s = new HashSet<>();
                    for (int[] e : dirs) {
                        if (check(i + e[0], j + e[1])) {
                            int root = find((i + e[0]) * n + j + e[1]);
                            if (!s.contains(root)) {
                                t += size[root];
                                s.add(root);
                            }
                        }
                    }
                    res = Math.max(res, t);
                }
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            size[pb] += size[pa];
            mx = Math.max(mx, size[pb]);
            p[pa] = pb;
        }
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 1;
    }
}