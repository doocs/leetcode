class Solution {
public:
    int oddCells(int m, int n, vector<vector<int>>& indices) {
        vector<vector<int>> g(m, vector<int>(n));
        for (auto& e : indices) {
            int r = e[0], c = e[1];
            for (int i = 0; i < m; ++i) ++g[i][c];
            for (int j = 0; j < n; ++j) ++g[r][j];
        }
        int ans = 0;
        for (auto& row : g)
            for (int v : row) ans += v % 2;
        return ans;
    }
};