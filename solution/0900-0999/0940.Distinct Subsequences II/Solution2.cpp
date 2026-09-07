class Solution {
public:
    int distinctSubseqII(string s) {
        const int mod = 1e9 + 7;
        int f[26]{};
        int ans = 0;
        for (char& c : s) {
            int i = c - 'a';
            int add = (ans + 1 + mod - f[i]) % mod;
            ans = (ans + add) % mod;
            f[i] = (f[i] + add) % mod;
        }
        return ans;
    }
};
