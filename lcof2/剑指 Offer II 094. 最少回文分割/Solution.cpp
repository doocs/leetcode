class Solution {
public:
    int minCut(string s) {
        int n = s.size();
        vector<vector<bool>> dp1(n, vector<bool>(n));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                dp1[i][j] = s[i] == s[j] && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        vector<int> dp2(n);
        for (int i = 0; i < n; ++i) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; ++j) {
                    if (dp1[j][i]) {
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
};
