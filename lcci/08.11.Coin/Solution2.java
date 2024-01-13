class Solution {
    public int waysToChange(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {25, 10, 5, 1};
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int c : coins) {
            for (int j = c; j <= n; ++j) {
                f[j] = (f[j] + f[j - c]) % mod;
            }
        }
        return f[n];
    }
}