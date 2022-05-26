class Solution {
public:
    int translateNum(int num) {
        // string s = to_string(num);
        // int n = s.size();
        // vector<int> dp(n + 1);
        // dp[0] = dp[1] = 1;
        // for (int i = 2; i <= n; ++i) {
        //     dp[i] = dp[i - 1];
        //     if (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] < '6') {
        //         dp[i] += dp[i - 2];
        //     }
        // }
        // return dp[n];
        string s = to_string(num);
        int n = s.size();
        int dp_0 = 1, dp_1 = 1, dp_2 = 1;
        for (int i = 2; i <= n; ++i) {
            dp_2 = dp_1;
            if (s[i - 2] == '1' || s[i - 2] == '2' && s[i - 1] < '6') {
                dp_2 += dp_0;
            }
            dp_0 = dp_1;
            dp_1 = dp_2;
        }
        return dp_2;
    }
};
