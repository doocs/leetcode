class Solution {
public:
    int findSubstringInWraproundString(string p) {
        vector<int> dp(26);
        int k = 0;
        for (int i = 0; i < p.size(); ++i) {
            char c = p[i];
            if (i && (c - p[i - 1] + 26) % 26 == 1)
                ++k;
            else
                k = 1;
            dp[c - 'a'] = max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int& v : dp) ans += v;
        return ans;
    }
};