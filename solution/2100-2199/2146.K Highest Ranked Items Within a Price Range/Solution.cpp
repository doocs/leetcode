class Solution {
public:
    vector<vector<int>> highestRankedKItems(vector<vector<int>>& grid, vector<int>& pricing, vector<int>& start, int k) {
        int m = grid.size(), n = grid[0].size();
        int row = start[0], col = start[1];
        int low = pricing[0], high = pricing[1];
        queue<pair<int, int>> q;
        q.push({row, col});
        vector<tuple<int, int, int, int>> pq;
        if (low <= grid[row][col] && grid[row][col] <= high) {
            pq.push_back({0, grid[row][col], row, col});
        }
        grid[row][col] = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int step = 1; q.size(); ++step) {
            int sz = q.size();
            for (int i = 0; i < sz; ++i) {
                auto [x, y] = q.front();
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int nx = x + dirs[j];
                    int ny = y + dirs[j + 1];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && grid[nx][ny] > 0) {
                        if (low <= grid[nx][ny] && grid[nx][ny] <= high) {
                            pq.push_back({step, grid[nx][ny], nx, ny});
                        }
                        grid[nx][ny] = 0;
                        q.push({nx, ny});
                    }
                }
            }
        }
        sort(pq.begin(), pq.end());
        vector<vector<int>> ans;
        for (int i = 0; i < min(k, (int) pq.size()); ++i) {
            ans.push_back({get<2>(pq[i]), get<3>(pq[i])});
        }
        return ans;
    }
};
