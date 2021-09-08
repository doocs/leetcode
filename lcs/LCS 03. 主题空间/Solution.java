class Solution {
    private int[] p;
    private int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int largestArea(String[] grid) {
        int m = grid.length, n = grid[0].length();
        p = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i].charAt(j) == '0') {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (int[] e : dirs) {
                        if (grid[i + e[0]].charAt(j + e[1]) == '0' || grid[i].charAt(j) == grid[i + e[0]].charAt(j + e[1])) {
                            p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (root != find(m * n)) {
                    mp.put(root, mp.getOrDefault(root, 0) + 1);
                    res = Math.max(res, mp.get(root));
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
}