class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs[0].size();
        vector<int> dp(n, 1);
        int mx = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (check(i, j, strs)) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            mx = max(mx, dp[i]);
        }
        return n - mx;
    }

    bool check(int i, int j, vector<string>& strs) {
        for (string& s : strs)
            if (s[i] < s[j])
                return false;
        return true;
    }
};