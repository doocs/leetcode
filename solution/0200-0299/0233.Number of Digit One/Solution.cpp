class Solution {
public:
    int a[12];
    int dp[12][12];

    int countDigitOne(int n) {
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int cnt, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};