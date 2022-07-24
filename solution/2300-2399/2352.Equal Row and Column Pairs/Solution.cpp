class Solution {
public:
    int equalPairs(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int j = 0; j < n; ++j)
            for (int i = 0; i < n; ++i)
                g[i][j] = grid[j][i];
        int ans = 0;
        for (auto row : grid)
            for (auto col : g)
                ans += row == col;
        return ans;
    }
};