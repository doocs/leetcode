class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        if (grid[0][0]) {
            return -1;
        }
        int n = grid.size();
        grid[0][0] = 1;
        queue<pair<int, int>> q;
        q.emplace(0, 0);
        for (int ans = 1; !q.empty(); ++ans) {
            for (int k = q.size(); k; --k) {
                auto [i, j] = q.front();
                q.pop();
                if (i == n - 1 && j == n - 1) {
                    return ans;
                }
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && !grid[x][y]) {
                            grid[x][y] = 1;
                            q.emplace(x, y);
                        }
                    }
                }
            }
        }
        return -1;
    }
};