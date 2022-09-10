class Solution {
    private int n;
    private long[] f;
    private int[] power;

    public long minimumTime(int[] power) {
        n = power.length;
        f = new long[1 << n];
        Arrays.fill(f, -1);
        this.power = power;
        return dfs(0);
    }

    private long dfs(int mask) {
        if (f[mask] != -1) {
            return f[mask];
        }
        int cnt = Integer.bitCount(mask);
        if (cnt == n) {
            return 0;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (((mask >> i) & 1) == 1) {
                continue;
            }
            ans = Math.min(ans, dfs(mask | 1 << i) + (power[i] + cnt) / (cnt + 1));
        }
        f[mask] = ans;
        return ans;
    }
}