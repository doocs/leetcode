class Solution {
public:
    int minIncrements(int n, vector<int>& cost) {
        int ans = 0;
        function<int(int)> dfs = [&](int i) -> int {
            if ((i << 1) > n) {
                return cost[i - 1];
            }
            int l = dfs(i << 1);
            int r = dfs(i << 1 | 1);
            ans += max(l, r) - min(l, r);
            return cost[i - 1] + max(l, r);
        };
        dfs(1);
        return ans;
    }
};