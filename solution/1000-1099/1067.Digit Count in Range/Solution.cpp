class Solution {
public:
    int d;
    int a[11];
    int dp[11][11];

    int digitsCount(int d, int low, int high) {
        this->d = d;
        return f(high) - f(low - 1);
    }

    int f(int n) {
        memset(dp, -1, sizeof dp);
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true, true);
    }

    int dfs(int pos, int cnt, bool lead, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!lead && !limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, cnt, lead, limit && i == up);
            } else {
                ans += dfs(pos - 1, cnt + (i == d), false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};