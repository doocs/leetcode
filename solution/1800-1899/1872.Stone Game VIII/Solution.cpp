class Solution {
public:
    int stoneGameVIII(vector<int>& stones) {
        int n = stones.size();
        for (int i = 1; i < n; ++i) {
            stones[i] += stones[i - 1];
        }
        int f[n];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n - 1) {
                return stones[i];
            }
            if (f[i] == -1) {
                f[i] = max(dfs(i + 1), stones[i] - dfs(i + 1));
            }
            return f[i];
        };
        return dfs(1);
    }
};