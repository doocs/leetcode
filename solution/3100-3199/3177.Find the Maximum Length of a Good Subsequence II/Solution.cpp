class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(k + 1));
        vector<unordered_map<int, int>> mp(k + 1);
        vector<vector<int>> g(k + 1, vector<int>(3));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                f[i][h] = mp[h][nums[i]];
                if (h > 0) {
                    if (g[h - 1][0] != nums[i]) {
                        f[i][h] = max(f[i][h], g[h - 1][1]);
                    } else {
                        f[i][h] = max(f[i][h], g[h - 1][2]);
                    }
                }
                ++f[i][h];
                mp[h][nums[i]] = max(mp[h][nums[i]], f[i][h]);
                if (g[h][0] != nums[i]) {
                    if (f[i][h] >= g[h][1]) {
                        g[h][2] = g[h][1];
                        g[h][1] = f[i][h];
                        g[h][0] = nums[i];
                    } else {
                        g[h][2] = max(g[h][2], f[i][h]);
                    }
                } else {
                    g[h][1] = max(g[h][1], f[i][h]);
                }
                ans = max(ans, f[i][h]);
            }
        }

        return ans;
    }
};