class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        Map<Double, Map<Double, Integer>> cnt1 = new HashMap<>(n * n);
        Map<Integer, Map<Double, Integer>> cnt2 = new HashMap<>(n * n);

        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                double k = dx == 0 ? Double.MAX_VALUE : 1.0 * dy / dx;
                double b = dx == 0 ? x1 : 1.0 * (y1 * dx - x1 * dy) / dx;
                if (k == -0.0) {
                    k = 0.0;
                }
                if (b == -0.0) {
                    b = 0.0;
                }
                cnt1.computeIfAbsent(k, _ -> new HashMap<>()).merge(b, 1, Integer::sum);
                int p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);
                cnt2.computeIfAbsent(p, _ -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;
        for (var e : cnt1.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans += s * t;
                s += t;
            }
        }
        for (var e : cnt2.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans -= s * t;
                s += t;
            }
        }
        return ans;
    }
}
