class Solution {
public:
    vector<int> solve(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int m = (int) sqrt(n);
        const int mod = 1e9 + 7;
        int suf[m + 1][n + 1];
        memset(suf, 0, sizeof(suf));
        for (int i = 1; i <= m; ++i) {
            for (int j = n - 1; ~j; --j) {
                suf[i][j] = (suf[i][min(n, j + i)] + nums[j]) % mod;
            }
        }
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1];
            if (y <= m) {
                ans.push_back(suf[y][x]);
            } else {
                int s = 0;
                for (int i = x; i < n; i += y) {
                    s = (s + nums[i]) % mod;
                }
                ans.push_back(s);
            }
        }
        return ans;
    }
};