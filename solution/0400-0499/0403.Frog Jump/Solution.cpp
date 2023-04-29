class Solution {
public:
    bool canCross(vector<int>& stones) {
        int n = stones.size();
        int f[n][n];
        memset(f, -1, sizeof(f));
        unordered_map<int, int> pos;
        for (int i = 0; i < n; ++i) {
            pos[stones[i]] = i;
        }
        function<bool(int, int)> dfs = [&](int i, int k) -> bool {
            if (i == n - 1) {
                return true;
            }
            if (f[i][k] != -1) {
                return f[i][k];
            }
            for (int j = k - 1; j <= k + 1; ++j) {
                if (j > 0 && pos.count(stones[i] + j) && dfs(pos[stones[i] + j], j)) {
                    return f[i][k] = true;
                }
            }
            return f[i][k] = false;
        };
        return dfs(0, 0);
    }
};