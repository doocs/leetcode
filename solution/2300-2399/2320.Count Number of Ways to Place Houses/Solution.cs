public class Solution {
    public int CountHousePlacements(int n) {
        const int mod = (int) 1e9 + 7;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = g[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = g[i - 1];
            g[i] = (f[i - 1] + g[i - 1]) % mod;
        }
        long v = (f[n - 1] + g[n - 1]) % mod;
        return (int) (v * v % mod);
    }
}