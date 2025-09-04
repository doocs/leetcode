public class Solution {
    public int NumberOfWays(int n, int x) {
        const int mod = 1000000007;
        int[,] f = new int[n + 1, n + 1];
        f[0, 0] = 1;
        for (int i = 1; i <= n; ++i) {
            long k = (long)Math.Pow(i, x);
            for (int j = 0; j <= n; ++j) {
                f[i, j] = f[i - 1, j];
                if (k <= j) {
                    f[i, j] = (f[i, j] + f[i - 1, j - (int)k]) % mod;
                }
            }
        }
        return f[n, n];
    }
}
