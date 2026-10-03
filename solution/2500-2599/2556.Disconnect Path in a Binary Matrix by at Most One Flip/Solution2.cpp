class Solution {
public:
    bool isPossibleToCutPath(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        auto dfs = [&]() {
            vector<pair<int, int>> stk{{0, 0}};
            while (!stk.empty()) {
                auto [i, j] = stk.back();
                stk.pop_back();
                if (i >= m || j >= n || grid[i][j] == 0) {
                    continue;
                }
                grid[i][j] = 0;
                if (i == m - 1 && j == n - 1) {
                    return true;
                }
                stk.emplace_back(i, j + 1);
                stk.emplace_back(i + 1, j);
            }
            return false;
        };
        bool a = dfs();
        grid[0][0] = grid[m - 1][n - 1] = 1;
        bool b = dfs();
        return !(a && b);
    }
};
