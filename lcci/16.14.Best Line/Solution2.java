class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, List<Integer>> cnt = new HashMap<>();
            List<Integer> dup = new ArrayList<>();
            for (int j = i + 1; j < n; ++j) {
                int dx = points[j][0] - x1, dy = points[j][1] - y1;
                if (dx == 0 && dy == 0) {
                    dup.add(j);
                    continue;
                }
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                String key = dx + "." + dy;
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(j);
            }
            if (cnt.isEmpty()) {
                if (!dup.isEmpty()) {
                    int c = dup.size() + 1;
                    if (better(mx, ans, c, i, dup.get(0))) {
                        mx = c;
                        ans[0] = i;
                        ans[1] = dup.get(0);
                    }
                }
                continue;
            }
            for (List<Integer> js : cnt.values()) {
                int b = js.get(0);
                if (!dup.isEmpty()) {
                    b = Math.min(b, dup.get(0));
                }
                int c = js.size() + dup.size() + 1;
                if (better(mx, ans, c, i, b)) {
                    mx = c;
                    ans[0] = i;
                    ans[1] = b;
                }
            }
        }
        return ans;
    }

    private boolean better(int mx, int[] ans, int c, int a, int b) {
        return c > mx || (c == mx && (a < ans[0] || (a == ans[0] && b < ans[1])));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
