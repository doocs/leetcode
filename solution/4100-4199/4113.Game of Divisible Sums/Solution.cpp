class Solution {
public:
    long long maxDifference(vector<int>& nums) {
        int n = nums.size();
        vector<long long> memo(n, LLONG_MIN);
        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (i >= n) {
                return 0;
            }
            if (memo[i] != LLONG_MIN) {
                return memo[i];
            }
            int x = nums[i];
            long long res = x % 3 == 0 ? x - dfs(i + 1) : x + dfs(i + 1);
            if (i + 1 < n) {
                long long s = x + nums[i + 1];
                long long t = s % 3 == 0 ? s - dfs(i + 2) : s + dfs(i + 2);
                res = max(res, t);
            }
            memo[i] = res;
            return res;
        };
        return dfs(0);
    }
};