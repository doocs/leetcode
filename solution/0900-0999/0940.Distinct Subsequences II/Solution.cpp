class Solution {
public:
    const int mod = 1e9 + 7;

    int distinctSubseqII(string s) {
        vector<long> dp(26);
        for (char& c : s) {
            int i = c - 'a';
            dp[i] = accumulate(dp.begin(), dp.end(), 1l) % mod;
        }
        return accumulate(dp.begin(), dp.end(), 0l) % mod;
    }
};