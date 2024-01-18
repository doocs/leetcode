class Solution {
public:
    int waysToChange(int n) {
        const int mod = 1e9 + 7;
        vector<int> coins = {25, 10, 5, 1};
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int c : coins) {
            for (int j = c; j <= n; ++j) {
                f[j] = (f[j] + f[j - c]) % mod;
            }
        }
        return f[n];
    }
};