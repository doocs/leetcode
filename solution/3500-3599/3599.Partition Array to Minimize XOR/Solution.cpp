class Solution {
public:
    int minXor(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> g(n + 1);
        for (int i = 1; i <= n; ++i) {
            g[i] = g[i - 1] ^ nums[i - 1];
        }

        const int inf = numeric_limits<int>::max();
        vector f(n + 1, vector(k + 1, inf));
        f[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(i, k); ++j) {
                for (int h = j - 1; h < i; ++h) {
                    f[i][j] = min(f[i][j], max(f[h][j - 1], g[i] ^ g[h]));
                }
            }
        }

        return f[n][k];
    }
};
