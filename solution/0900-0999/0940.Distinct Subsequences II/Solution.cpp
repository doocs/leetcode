class Solution {
public:
    const int mod = 1e9 + 7;

    int distinctSubseqII(string s) {
        vector<long> dp(26);
        long ans = 0;
        for (char& c : s) {
            int i = c - 'a';
            long add = ans - dp[i] + 1;
            ans = (ans + add + mod) % mod;
            dp[i] = (dp[i] + add) % mod;
        }
        return ans;
    }
};