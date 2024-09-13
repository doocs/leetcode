class Solution {
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] f = new long[1 << n];
        Arrays.fill(f, Long.MAX_VALUE);
        f[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int gain = Integer.bitCount(mask);
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    f[mask] = Math.min(f[mask], f[mask ^ 1 << i] + (power[i] + gain - 1) / gain);
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
