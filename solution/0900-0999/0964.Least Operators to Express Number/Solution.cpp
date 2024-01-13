class Solution {
public:
    int leastOpsExpressTarget(int x, int target) {
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int v) -> int {
            if (x >= v) {
                return min(v * 2 - 1, 2 * (x - v));
            }
            if (f.count(v)) {
                return f[v];
            }
            int k = 2;
            long long y = x * x;
            while (y < v) {
                y *= x;
                ++k;
            }
            int ans = k - 1 + dfs(v - y / x);
            if (y - v < v) {
                ans = min(ans, k + dfs(y - v));
            }
            f[v] = ans;
            return ans;
        };
        return dfs(target);
    }
};