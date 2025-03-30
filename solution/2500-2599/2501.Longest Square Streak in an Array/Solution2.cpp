class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<long long, int> f;
        auto dfs = [&](this auto&& dfs, long long x) -> int {
            if (!s.contains(x)) {
                return 0;
            }
            if (f.contains(x)) {
                return f[x];
            }
            f[x] = 1 + dfs(x * x);
            return f[x];
        };
        for (long long x : s) {
            ans = max(ans, dfs(x));
        }
        return ans < 2 ? -1 : ans;
    }
};