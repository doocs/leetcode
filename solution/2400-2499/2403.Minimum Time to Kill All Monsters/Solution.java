class Solution {
    private int n;
    private int[] power;
    private Long[] f;

    public long minimumTime(int[] power) {
        n = power.length;
        this.power = power;
        f = new Long[1 << n];
        return dfs((1 << n) - 1);
    }

    private long dfs(int mask) {
        if (mask == 0) {
            return 0;
        }
        if (f[mask] != null) {
            return f[mask];
        }
        f[mask] = Long.MAX_VALUE;
        int gain = 1 + (n - Integer.bitCount(mask));
        for (int i = 0; i < n; ++i) {
            if ((mask >> i & 1) == 1) {
                f[mask] = Math.min(f[mask], dfs(mask ^ 1 << i) + (power[i] + gain - 1) / gain);
            }
        }
        return f[mask];
    }
}
