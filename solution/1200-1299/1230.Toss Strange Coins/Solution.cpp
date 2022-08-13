class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        vector<double> dp(target + 1);
        dp[0] = 1;
        for (auto v : prob) {
            for (int j = target; j >= 0; --j) {
                dp[j] *= (1 - v);
                if (j >= 1) dp[j] += dp[j - 1] * v;
            }
        }
        return dp[target];
    }
};