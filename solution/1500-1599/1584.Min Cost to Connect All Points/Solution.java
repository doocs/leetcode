class Solution {
    private int[] p;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j =  i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                edges.add(new int[]{Math.abs(x1 - x2) + Math.abs(y1 - y2), i, j});
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[0]));
        int res = 0;
        for (int[] e : edges) {
            if (find(e[1]) == find(e[2])) {
                continue;
            }
            p[find(e[1])] = find(e[2]);
            --n;
            res += e[0];
            if (n == 1) {
                break;
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