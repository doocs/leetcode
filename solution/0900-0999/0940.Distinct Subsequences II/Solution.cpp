class Solution {
public:
    int distinctSubseqII(string s) {
        const int mod = 1e9 + 7;
        int f[26]{};
        for (char& c : s) {
            int x = 1;
            for (int v : f) {
                x = (x + v) % mod;
            }
            f[c - 'a'] = x;
        }
        int ans = 0;
        for (int v : f) {
            ans = (ans + v) % mod;
        }
        return ans;
    }
};
