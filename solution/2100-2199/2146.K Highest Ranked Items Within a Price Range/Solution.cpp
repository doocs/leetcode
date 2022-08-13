class Solution {
public:
    vector<vector<int>> highestRankedKItems(vector<vector<int>>& grid, vector<int>& pricing, vector<int>& start, int k) {
        int m = grid.size(), n = grid[0].size();
        int row = start[0], col = start[1];
        int low = pricing[0], high = pricing[1];
        vector<tuple<int, int, int, int>> items;
        if (low <= grid[row][col] && grid[row][col] <= high)
            items.emplace_back(0, grid[row][col], row, col);
        queue<tuple<int, int, int>> q;
        q.emplace(row, col, 0);
        grid[row][col] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            auto [i, j, d] = q.front();
            q.pop();
            for (int l = 0; l < 4; ++l) {
                int x = i + dirs[l], y = j + dirs[l + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y]) {
                    if (low <= grid[x][y] && grid[x][y] <= high) items.emplace_back(d + 1, grid[x][y], x, y);
                    grid[x][y] = 0;
                    q.emplace(x, y, d + 1);
                }
            }
        }
        sort(items.begin(), items.end());
        vector<vector<int>> ans;
        for (int i = 0; i < items.size() && i < k; ++i) {
            auto [d, p, x, y] = items[i];
            ans.push_back({x, y});
        }
        return ans;
    }
};