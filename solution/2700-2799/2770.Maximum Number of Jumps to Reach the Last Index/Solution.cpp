class Solution {
public:
    int maximumJumps(vector<int>& nums, int target) {
        int n = nums.size();
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) {
            if (i == n - 1) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = -(1 << 30);
            for (int j = i + 1; j < n; ++j) {
                if (abs(nums[i] - nums[j]) <= target) {
                    f[i] = max(f[i], 1 + dfs(j));
                }
            }
            return f[i];
        };
        int ans = dfs(0);
        return ans < 0 ? -1 : ans;
    }
};