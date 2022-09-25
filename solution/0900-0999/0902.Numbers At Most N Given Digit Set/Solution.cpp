class Solution {
public:
    int a[12];
    int dp[12][2];
    unordered_set<int> s;

    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        memset(dp, -1, sizeof dp);
        for (auto& d : digits) {
            s.insert(stoi(d));
        }
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    int dfs(int pos, int lead, bool limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && !lead && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.count(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && !lead) {
            dp[pos][lead] = ans;
        }
        return ans;
    }
};