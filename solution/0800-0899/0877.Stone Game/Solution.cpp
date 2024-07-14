class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> int {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            return f[i][j] = max(piles[i] - dfs(dfs, i + 1, j), piles[j] - dfs(dfs, i, j - 1));
        };
        return dfs(dfs, 0, n - 1) > 0;
    }
};