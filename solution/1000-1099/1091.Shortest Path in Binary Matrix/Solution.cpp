class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        if (grid[0][0]) return -1;
        int n = grid.size();
        queue<pair<int, int>> q;
        q.push({0, 0});
        grid[0][0] = 1;
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int m = q.size(); m > 0; --m) {
                auto p = q.front();
                q.pop();
                int i = p.first, j = p.second;
                if (i == n - 1 && j == n - 1) return ans;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            q.push({x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
};