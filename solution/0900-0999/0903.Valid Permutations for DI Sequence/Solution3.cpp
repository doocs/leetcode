class Solution {
public:
    int numPermsDISequence(string s) {
        const int mod = 1e9 + 7;
        int n = s.size();
        vector<int> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int pre = 0;
            vector<int> g(n + 1);
            if (s[i - 1] == 'D') {
                for (int j = i; j >= 0; --j) {
                    pre = (pre + f[j]) % mod;
                    g[j] = pre;
                }
            } else {
                for (int j = 0; j <= i; ++j) {
                    g[j] = pre;
                    pre = (pre + f[j]) % mod;
                }
            }
            f = move(g);
        }
        int ans = 0;
        for (int j = 0; j <= n; ++j) {
            ans = (ans + f[j]) % mod;
        }
        return ans;
    }
};