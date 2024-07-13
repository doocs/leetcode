class Solution {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int n = cost.size();
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] < 0) {
                f[i] = cost[i] + min(dfs(dfs, i + 1), dfs(dfs, i + 2));
            }
            return f[i];
        };
        return min(dfs(dfs, 0), dfs(dfs, 1));
    }
};
