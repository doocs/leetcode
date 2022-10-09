class Solution {
public:
    int numberOfPaths(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        int mod = 1e9 + 7;
        vector<vector<vector<int>>> f(m, vector<vector<int>>(n, vector<int>(k, -1)));
        function<int(int, int, int)> dfs;
        dfs = [&](int i, int j, int s) {
            if (i < 0 || i >= m || j < 0 || j >= n) return 0;
            s = (s + grid[i][j]) % k;
            if (i == m - 1 && j == n - 1) return s == 0 ? 1 : 0;
            if (f[i][j][s] != -1) return f[i][j][s];
            int ans = dfs(i + 1, j, s) + dfs(i, j + 1, s);
            ans %= mod;
            f[i][j][s] = ans;
            return ans;
        };
        return dfs(0, 0, 0);
    }
};