class Solution {
public:
    int getKthMagicNumber(int k) {
        vector<int> dp(k + 1, 1);
        int p3 = 1, p5 = 1, p7 = 1;
        for (int i = 2; i <= k; ++i) {
            int a = dp[p3] * 3, b = dp[p5] * 5, c = dp[p7] * 7;
            int v = min(min(a, b), c);
            dp[i] = v;
            if (v == a) {
                ++p3;
            }
            if (v == b) {
                ++p5;
            }
            if (v == c) {
                ++p7;
            }
        }
        return dp[k];
    }
};