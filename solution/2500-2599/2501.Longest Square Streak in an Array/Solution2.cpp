class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<long long> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int x) -> int {
            if (!s.count(x)) return 0;
            if (f.count(x)) return f[x];
            long long t = 1ll * x * x;
            if (t > INT_MAX) return 1;
            f[x] = 1 + dfs(x * x);
            return f[x];
        };
        for (int& v : nums) ans = max(ans, dfs(v));
        return ans < 2 ? -1 : ans;
    }
};