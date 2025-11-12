class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] f = new int[n + 1];
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = costs[i - 1];
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                f[i] = Math.min(f[i], f[j] + x + (i - j) * (i - j));
            }
        }
        return f[n];
    }
}
