class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int m = ranges::max(nums);
        vector f(m + 1, vector<int>(m + 1));
        f[0][0] = 1;
        for (int x : nums) {
            vector g(m + 1, vector<int>(m + 1));
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    g[gcd(x, j)][k] = (g[gcd(x, j)][k] + v) % MOD;
                    g[j][gcd(x, k)] = (g[j][gcd(x, k)] + v) % MOD;
                }
            }
            f.swap(g);
        }
        long long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (ans - 1 + MOD) % MOD;
    }
};
