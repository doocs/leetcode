class Solution {
    private int[] p;

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i < m - 1) {
                    edges.add(new int[]{Math.abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                }
                if (j < n - 1) {
                    edges.add(new int[]{Math.abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
                }
            }
        }
        Collections.sort(edges, Comparator.comparingInt(a -> a[0]));
        for (int[] e : edges) {
            int i = e[1], j = e[2];
            p[find(i)] = find(j);
            if (find(0) == find(m * n - 1)) {
                return e[0];
            }
        }
        return 0;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}