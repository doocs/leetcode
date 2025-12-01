class Solution {
    public int countTrapezoids(int[][] points) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var p : points) {
            cnt.merge(p[1], 1, Integer::sum);
        }
        long ans = 0, s = 0;
        for (int v : cnt.values()) {
            long t = 1L * v * (v - 1) / 2;
            ans = (ans + s * t) % mod;
            s += t;
        }
        return (int) ans;
    }
}
