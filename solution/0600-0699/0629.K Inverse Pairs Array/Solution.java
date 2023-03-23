class Solution {
    public int kInversePairs(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        int[] s = new int[k + 2];
        f[0] = 1;
        Arrays.fill(s, 1);
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[Math.max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
}