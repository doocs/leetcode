class Solution {
    public double maxPrice(int[][] items, int capacity) {
        Arrays.sort(items, (a, b) -> a[1] * b[0] - a[0] * b[1]);
        double ans = 0;
        for (var e : items) {
            int p = e[0], w = e[1];
            int v = Math.min(w, capacity);
            ans += v * 1.0 / w * p;
            capacity -= v;
        }
        return capacity > 0 ? -1 : ans;
    }
}