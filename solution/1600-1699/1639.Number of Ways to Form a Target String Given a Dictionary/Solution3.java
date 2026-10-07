class Solution {
    public int numWays(String[] words, String target) {
        int m = target.length();
        int n = words[0].length();
        final int mod = (int) 1e9 + 7;
        int[][] cnt = new int[n][26];
        for (var w : words) {
            for (int j = 0; j < n; ++j) {
                cnt[j][w.charAt(j) - 'a']++;
            }
        }
        int[][] f = new int[m + 1][n + 1];
        for (int j = 0; j <= n; ++j) {
            f[m][j] = 1;
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                long ans = f[i][j + 1];
                ans += 1L * f[i + 1][j + 1] * cnt[j][target.charAt(i) - 'a'];
                f[i][j] = (int) (ans % mod);
            }
        }
        return f[0][0];
    }
}
