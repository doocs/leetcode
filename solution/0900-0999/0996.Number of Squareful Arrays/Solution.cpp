class Solution {
public:
    int numSquarefulPerms(vector<int>& nums) {
        int n = nums.size();
        int f[1 << n][n];
        memset(f, 0, sizeof(f));
        for (int j = 0; j < n; ++j) {
            f[1 << j][j] = 1;
        }
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    for (int k = 0; k < n; ++k) {
                        if ((i >> k & 1) == 1 && k != j) {
                            int s = nums[j] + nums[k];
                            int t = sqrt(s);
                            if (t * t == s) {
                                f[i][j] += f[i ^ (1 << j)][k];
                            }
                        }
                    }
                }
            }
        }
        long long ans = 0;
        for (int j = 0; j < n; ++j) {
            ans += f[(1 << n) - 1][j];
        }
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int g[13] = {1};
        for (int i = 1; i < 13; ++i) {
            g[i] = g[i - 1] * i;
        }
        for (auto& [_, v] : cnt) {
            ans /= g[v];
        }
        return ans;
    }
};