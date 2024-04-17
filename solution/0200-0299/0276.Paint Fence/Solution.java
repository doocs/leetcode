class Solution {
    public int numWays(int n, int k) {
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
}