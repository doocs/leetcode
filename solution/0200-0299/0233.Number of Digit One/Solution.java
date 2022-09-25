class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][12];

    public int countDigitOne(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
}