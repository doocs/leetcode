class Solution {
public:
    int numRollsToTarget(int n, int k, int target) {
        const int mod = 1e9 + 7;
        vector<int> f(target + 1);
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            vector<int> g(target + 1);
            for (int j = 1; j <= min(target, i * k); ++j) {
                for (int h = 1; h <= min(j, k); ++h) {
                    g[j] = (g[j] + f[j - h]) % mod;
                }
            }
            f = move(g);
        }
        return f[target];
    }
};