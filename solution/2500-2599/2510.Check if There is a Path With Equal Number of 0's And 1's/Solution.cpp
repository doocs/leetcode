class Solution {
public:
    bool isThereAPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int s = m + n - 1;
        if (s & 1) return false;
        int f[m][n][s];
        s >>= 1;
        memset(f, -1, sizeof f);
        function<bool(int, int, int)> dfs = [&](int i, int j, int k) -> bool {
            if (i >= m || j >= n) return false;
            k += grid[i][j];
            if (f[i][j][k] != -1) return f[i][j][k];
            if (k > s || i + j + 1 - k > s) return false;
            if (i == m - 1 && j == n - 1) return k == s;
            f[i][j][k] = dfs(i + 1, j, k) || dfs(i, j + 1, k);
            return f[i][j][k];
        };
        return dfs(0, 0, 0);
    }
};