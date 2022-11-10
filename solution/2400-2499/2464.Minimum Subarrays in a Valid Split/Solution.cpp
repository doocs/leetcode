class Solution {
public:
    const int inf = 0x3f3f3f3f;
    int validSubarraySplit(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n);
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) return 0;
            if (f[i]) return f[i];
            int ans = inf;
            for (int j = i; j < n; ++j) {
                if (__gcd(nums[i], nums[j]) > 1) {
                    ans = min(ans, 1 + dfs(j + 1));
                }
            }
            f[i] = ans;
            return ans;
        };
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }
};