class Solution {
public:
    vector<int> findPermutation(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans;
        int f[1 << n][n];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int mask, int pre) {
            if (mask == (1 << n) - 1) {
                return abs(pre - nums[0]);
            }
            int* res = &f[mask][pre];
            if (*res != -1) {
                return *res;
            }
            *res = INT_MAX;
            for (int cur = 1; cur < n; ++cur) {
                if (mask >> cur & 1 ^ 1) {
                    *res = min(*res, abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur));
                }
            }
            return *res;
        };
        function<void(int, int)> g = [&](int mask, int pre) {
            ans.push_back(pre);
            if (mask == (1 << n) - 1) {
                return;
            }
            int res = dfs(mask, pre);
            for (int cur = 1; cur < n; ++cur) {
                if (mask >> cur & 1 ^ 1) {
                    if (abs(pre - nums[cur]) + dfs(mask | 1 << cur, cur) == res) {
                        g(mask | 1 << cur, cur);
                        break;
                    }
                }
            }
        };
        g(1, 0);
        return ans;
    }
};