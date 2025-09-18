class Solution {
    public long maxArea(int[][] coords) {
        long ans = calc(coords);
        for (int[] c : coords) {
            int tmp = c[0];
            c[0] = c[1];
            c[1] = tmp;
        }
        ans = Math.max(ans, calc(coords));
        return ans > 0 ? ans : -1;
    }

    private long calc(int[][] coords) {
        int mn = Integer.MAX_VALUE, mx = 0;
        Map<Integer, Integer> f = new HashMap<>();
        Map<Integer, Integer> g = new HashMap<>();

        for (int[] c : coords) {
            int x = c[0], y = c[1];
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
            if (f.containsKey(x)) {
                f.put(x, Math.min(f.get(x), y));
                g.put(x, Math.max(g.get(x), y));
            } else {
                f.put(x, y);
                g.put(x, y);
            }
        }

        long ans = 0;
        for (var e : f.entrySet()) {
            int x = e.getKey();
            int y = e.getValue();
            int d = g.get(x) - y;
            ans = Math.max(ans, (long) d * Math.max(mx - x, x - mn));
        }
        return ans;
    }
}
